package com.freelance.service;

import java.util.List;

import com.freelance.entities.TaskEntity;

public interface TaskService {
	
	TaskEntity createTask(TaskEntity task);
	public List<TaskEntity> getAllTasks();
    List<TaskEntity> getTasksByFreelancer(Long freelancerId);
    TaskEntity markCompleted(Long id);
	TaskEntity markPaid(Long taskId, Long payerId, Double amount);

}
