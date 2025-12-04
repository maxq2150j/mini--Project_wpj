package com.freelance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freelance.entities.PaymentEntity;
import com.freelance.repository.PaymentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private final PaymentRepository paymentRepository;
	
	@Override
    public PaymentEntity requestPayment(PaymentEntity payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public List<PaymentEntity> getPaymentsByFreelancer(Long freelancerId) {
        // return most recent payments first
        return paymentRepository.findByFreelancerIdOrderByIdDesc(freelancerId);
    }

}
