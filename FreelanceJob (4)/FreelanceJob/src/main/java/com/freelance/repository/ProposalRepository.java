package com.freelance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelance.entities.ProposalEntity;

public interface ProposalRepository extends JpaRepository <ProposalEntity, Long> {
	
	 List<ProposalEntity> findByFreelancerId(Long freelancerId);
	
	List<ProposalEntity> findByJobId(Long jobId);

    List<ProposalEntity> findByJobIdIn(java.util.List<Long> jobIds);

}
