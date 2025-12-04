package com.freelance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelance.entities.ClientPaymentEntity;



public interface ClientPaymentRepository extends JpaRepository<ClientPaymentEntity, Long> {
	
	List<ClientPaymentEntity> findByFreelancerId(Long freelancerId);
	

}
