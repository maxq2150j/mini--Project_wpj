package com.freelance.service;

import java.util.List;

import com.freelance.entities.ClientPaymentEntity;

public interface ClientPaymentService {
	
	 ClientPaymentEntity requestPayment(ClientPaymentEntity p);
	    List<ClientPaymentEntity> getByFreelancer(Long freelancerId);

}
