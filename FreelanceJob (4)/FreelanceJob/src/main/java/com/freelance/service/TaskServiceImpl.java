package com.freelance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.freelance.entities.TaskEntity;
import com.freelance.entities.PaymentEntity;
import com.freelance.repository.TaskRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service 
@Transactional
@AllArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService{
    
	private final TaskRepository taskRepository;
	private final PaymentService paymentService;
	private final SseService sseService;
	
	@Override
	public TaskEntity createTask(TaskEntity task) {
		task.setStatus("assigned");
		task.setPaid(false);
		return taskRepository.save(task);
		
		
	}
	@Override
	public List<TaskEntity> getAllTasks() {
	    return taskRepository.findAll();
	}

	
	@Override 
	public List<TaskEntity> getTasksByFreelancer(Long freelancerId){
		
		return taskRepository.findByFreelancerId(freelancerId);
		
	}
	
	@Override
    public TaskEntity markCompleted(Long id) {
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus("completed");
        return taskRepository.save(task);
    }

	@Override
	public TaskEntity markPaid(Long taskId, Long payerId, Double amount) {
		TaskEntity task = taskRepository.findById(taskId)
				.orElseThrow(() -> new RuntimeException("Task not found"));
		task.setPaid(true);
		if (amount != null) task.setAmount(amount);
		TaskEntity saved = taskRepository.save(task);

		try {
			PaymentEntity p = new PaymentEntity();
			p.setTaskId(saved.getId());
			p.setFreelancerId(saved.getFreelancerId());
			p.setPayerId(payerId);
			p.setAmount(saved.getAmount() != null ? saved.getAmount() : amount);
			p.setStatus("PAID");
			PaymentEntity savedPayment = paymentService.requestPayment(p);
			if (savedPayment != null) {
				log.info("Recorded payment id={} for task={}, freelancer={}, amount={}", savedPayment.getId(), savedPayment.getTaskId(), savedPayment.getFreelancerId(), savedPayment.getAmount());
				// push the payment event to any SSE subscribers for this freelancer
				try {
					if (savedPayment.getFreelancerId() != null) {
						sseService.sendPayment(savedPayment.getFreelancerId(), savedPayment);
					}
				} catch (Exception ex2) {
					log.error("Failed to send SSE for payment {}: {}", savedPayment.getId(), ex2.getMessage());
				}
			} else {
				log.warn("PaymentService returned null when recording payment for task {}", taskId);
			}
		} catch (Exception ex) {
			log.error("Failed to record payment for task {}: {}", taskId, ex.getMessage(), ex);
		}

		return saved;
	}

}
