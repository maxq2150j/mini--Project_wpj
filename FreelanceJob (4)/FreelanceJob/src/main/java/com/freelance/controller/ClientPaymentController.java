package com.freelance.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelance.entities.ClientPaymentEntity;

import com.freelance.service.ClientPaymentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ClientPaymentController {
    private final ClientPaymentService clientpaymentService;

    @PostMapping("/request")
    public ClientPaymentEntity requestPayment(@RequestBody ClientPaymentEntity p) { return clientpaymentService.requestPayment(p); }

    @GetMapping("/freelancer/{id}")
    public List<ClientPaymentEntity> getByFreelancer(@PathVariable Long id) { return clientpaymentService.getByFreelancer(id); }
}
