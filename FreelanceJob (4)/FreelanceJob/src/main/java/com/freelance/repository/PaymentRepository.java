package com.freelance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelance.entities.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
	
	List<PaymentEntity> findByFreelancerId(Long freelancerId);

	// return most recent payments first
	List<PaymentEntity> findByFreelancerIdOrderByIdDesc(Long freelancerId);

}
