package com.freelance.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.freelance.entities.ClientTaskEntity;
import com.freelance.service.ClientTaskService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/client/tasks")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ClientTaskController {

	private final ClientTaskService clienttaskService;
	
	
	
	 @GetMapping
	    public List<ClientTaskEntity> getClientTasks(@RequestParam(required = false) Long clientId) {
	        if (clientId == null) return clienttaskService.getAllTasks();
	        return clienttaskService.getTasksByClient(clientId);
	    }
	 
	 @PostMapping("/{id}/pay")
	    public ClientTaskEntity payTask(@PathVariable Long id, @RequestParam Long payerId, @RequestParam Double amount) {
	        return clienttaskService.markPaid(id, payerId, amount);
	    }
}
