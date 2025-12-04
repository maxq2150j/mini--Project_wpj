package com.freelance.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.freelance.dto.ProposalRequestDTO;
import com.freelance.entities.ProposalEntity;
import com.freelance.repository.ProposalRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ProposalServiceImpl implements ProposalService {

    private final ProposalRepository proposalRepository;
    private final ModelMapper mapper;
    private final com.freelance.repository.ClientJobRepository clientJobRepository;

    @Override
    public String createProposal(ProposalRequestDTO dto) {
        ProposalEntity entity = mapper.map(dto, ProposalEntity.class);
        entity.setStatus("PENDING");
        proposalRepository.save(entity);
        return "Proposal sent Successfully";
    }

    @Override
    public ProposalEntity updateProposal(Long id, ProposalRequestDTO dto) {
        ProposalEntity old = proposalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proposal Not Found"));

        if (dto.getPrice() != null) old.setPrice(dto.getPrice());
        if (dto.getMessage() != null) old.setMessage(dto.getMessage());
        if (dto.getStatus() != null) old.setStatus(dto.getStatus());

        return proposalRepository.save(old);
    }

    @Override
    public ProposalEntity getProposalById(Long id) {
        return proposalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proposal not found with id : " + id));
    }

    @Override
    public List<ProposalEntity> getAllProposals() {
        return proposalRepository.findAll();
    }

    @Override
    public List<ProposalEntity> getProposalsByFreelancer(Long freelancerId) {
        return proposalRepository.findByFreelancerId(freelancerId);
    }

    @Override
    public List<ProposalEntity> getProposalsByJob(Long jobId) {
        return proposalRepository.findByJobId(jobId);
    }

    @Override
    public List<ProposalEntity> getProposalsByClient(Long clientId) {
        List<com.freelance.entities.ClientJobEntity> jobs = clientJobRepository.findByClientId(clientId);
        if (jobs == null || jobs.isEmpty()) return new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        for (com.freelance.entities.ClientJobEntity j : jobs) ids.add(j.getId());
        return proposalRepository.findByJobIdIn(ids);
    }

    @Override
    public String deleteProposal(Long id) {
        proposalRepository.deleteById(id);
        return "Proposal deleted Sucessfully";
    }
}
