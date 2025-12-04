package com.freelance.service;

import java.util.List;

import com.freelance.dto.ProposalRequestDTO;

import com.freelance.entities.ProposalEntity;

public interface ProposalService {
	
	 String createProposal(ProposalRequestDTO dto);

	    ProposalEntity updateProposal(Long id, ProposalRequestDTO dto);

	    ProposalEntity getProposalById(Long id);

	    List<ProposalEntity> getAllProposals();

	    List<ProposalEntity> getProposalsByFreelancer(Long freelancerId);

	List<ProposalEntity> getProposalsByJob(Long jobId);

	List<ProposalEntity> getProposalsByClient(Long clientId);

	    String deleteProposal(Long id);

}
