package com.freelance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelance.entities.ClientTaskEntity;


public interface ClientTaskRepository extends JpaRepository<ClientTaskEntity, Long>{
	
	List<ClientTaskEntity> findByClientId(Long clientId);
	List<ClientTaskEntity> findByFreelancerId(Long freelancerId);

}
