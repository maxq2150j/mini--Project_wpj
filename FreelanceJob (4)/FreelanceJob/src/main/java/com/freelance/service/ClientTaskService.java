package com.freelance.service;

import java.util.List;

import com.freelance.entities.ClientTaskEntity;

public interface ClientTaskService {

	List<ClientTaskEntity> getAllTasks();

    List<ClientTaskEntity> getTasksByClient(Long clientId);
    ClientTaskEntity createTask(ClientTaskEntity task);


    ClientTaskEntity markPaid(Long taskId, Long freelancerId, Double amount);
}


