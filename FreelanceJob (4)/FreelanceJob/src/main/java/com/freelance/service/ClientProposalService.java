package com.freelance.service;

import java.util.List;

import com.freelance.entities.ClientProposalEntity;

public interface ClientProposalService {
	ClientProposalEntity createProposal(ClientProposalEntity p);
    List<ClientProposalEntity> getByJob(Long jobId);
    List<ClientProposalEntity> getAll();
    ClientProposalEntity updateStatus(Long id, String status);

}
