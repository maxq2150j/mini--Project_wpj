package com.freelance.service;

import java.util.List;

import com.freelance.entities.PaymentEntity;

public interface PaymentService {
	
	 PaymentEntity requestPayment(PaymentEntity payment);

	    List<PaymentEntity> getPaymentsByFreelancer(Long freelancerId);

}
