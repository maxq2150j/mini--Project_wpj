package com.freelance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelance.entities.ClientProposalEntity;





public interface ClientProposalRepository extends JpaRepository<ClientProposalEntity, Long> {

    List<ClientProposalEntity> findByClientId(Long clientId);

    List<ClientProposalEntity> findByJobId(Long jobId);
}
