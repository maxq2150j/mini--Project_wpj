package com.freelance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.freelance.entities.ClientProposalEntity;
import com.freelance.entities.ClientTaskEntity;
import com.freelance.repository.ClientProposalRepository;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor

public class ClientProposalServiceImpl implements ClientProposalService {
	
	private final ClientProposalRepository clientproposalRepository;

	private final ClientTaskService clienttaskService;
	
	
	
	@Override
	public ClientProposalEntity createProposal(ClientProposalEntity p) {
        p.setStatus("PENDING");
        return clientproposalRepository.save(p);
    }
	
	 @Override
	 public List<ClientProposalEntity> getByJob(Long jobId) {
		 return clientproposalRepository.findByJobId(jobId); 
		 }
	    @Override 
	    public List<ClientProposalEntity> getAll() { 
	    	return clientproposalRepository.findAll(); 
	    	}

	    
	    
	    @Override
	    public ClientProposalEntity updateStatus(Long id, String status) {
	        ClientProposalEntity old = clientproposalRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
	        old.setStatus(status.toUpperCase());
	        ClientProposalEntity saved = clientproposalRepository.save(old);

			// NOTE: task creation is performed by the client when they provide assignment details
			// to avoid duplicate/partial tasks being created here.
	        return saved;
	    }
}
