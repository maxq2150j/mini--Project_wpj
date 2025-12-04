package com.freelance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.freelance.entities.ClientTaskEntity;
import com.freelance.repository.ClientTaskRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
@Service
@Transactional
@AllArgsConstructor
public class ClientTaskServiceImpl implements ClientTaskService {
	private final ClientTaskRepository clientTaskRepository;
	
	@Override
	public ClientTaskEntity createTask(ClientTaskEntity task) {
	    return clientTaskRepository.save(task);
	}

	 @Override
	    public List<ClientTaskEntity> getAllTasks() {
	        return clientTaskRepository.findAll();
	    }

	    @Override
	    public List<ClientTaskEntity> getTasksByClient(Long clientId) {
	        return clientTaskRepository.findByClientId(clientId);
	    }

	    @Override
	    public ClientTaskEntity markPaid(Long taskId, Long freelancerId, Double amount) {
	        ClientTaskEntity task = clientTaskRepository.findById(taskId)
	                .orElseThrow(() -> new RuntimeException("Task not found"));

	        task.setPaid(true);
	        task.setFreelancerId(freelancerId);
	        task.setAmount(amount);

	        return clientTaskRepository.save(task);
	    }
}
