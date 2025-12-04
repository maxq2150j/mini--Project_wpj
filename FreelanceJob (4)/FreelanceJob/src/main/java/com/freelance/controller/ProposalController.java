package com.freelance.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelance.dto.ProposalRequestDTO;
import com.freelance.service.ProposalService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/proposals")
@AllArgsConstructor
public class ProposalController {
	
	private final ProposalService service;
	
	@PostMapping 
	 public ResponseEntity<?> send(@RequestBody ProposalRequestDTO dto){
		return ResponseEntity.ok(service.createProposal(dto));
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProposalRequestDTO dto) {
        return ResponseEntity.ok(service.updateProposal(id, dto));
    }

    @GetMapping("/freelancer/{fid}")
    public ResponseEntity<?> getByFreelancer(@PathVariable Long fid) {
        return ResponseEntity.ok(service.getProposalsByFreelancer(fid));
    }

    @GetMapping("/job/{jid}")
    public ResponseEntity<?> getByJob(@PathVariable Long jid) {
        return ResponseEntity.ok(service.getProposalsByJob(jid));
    }

    @GetMapping("/client/{cid}")
    public ResponseEntity<?> getByClient(@PathVariable Long cid) {
        return ResponseEntity.ok(service.getProposalsByClient(cid));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteProposal(id));
    }
	
	


}
