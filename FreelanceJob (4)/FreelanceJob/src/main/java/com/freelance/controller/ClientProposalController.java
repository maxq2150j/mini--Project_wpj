package com.freelance.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelance.entities.ClientProposalEntity;
import com.freelance.service.ClientProposalService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/client")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ClientProposalController {
	
	private final ClientProposalService clientproposalService;

    @GetMapping("/proposals")
    public List<ClientProposalEntity> getAll() {
    	return clientproposalService.getAll(); }

    // accept/reject endpoint used by your React code
    @PostMapping("/applications/{appId}/{status}")
    public ClientProposalEntity updateStatus(@PathVariable Long appId, @PathVariable String status) {
        return clientproposalService.updateStatus(appId, status);
    }

}
