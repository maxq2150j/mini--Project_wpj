package com.freelance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.freelance.entities.ClientPaymentEntity;
import com.freelance.repository.ClientPaymentRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ClientPaymentServiceImpl implements ClientPaymentService {
	
	private final ClientPaymentRepository clientpaymentRepository;
	
	@Override 
	public ClientPaymentEntity requestPayment(ClientPaymentEntity p) { 
		return clientpaymentRepository.save(p);
	}
    @Override
    public List<ClientPaymentEntity> getByFreelancer(Long freelancerId) { 
    	return clientpaymentRepository.findByFreelancerId(freelancerId); 
    	}

}
