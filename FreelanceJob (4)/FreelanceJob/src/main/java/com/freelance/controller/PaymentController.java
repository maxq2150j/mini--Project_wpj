package com.freelance.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelance.entities.PaymentEntity;
import com.freelance.service.PaymentService;
import com.freelance.service.SseService;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/payments")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {

    private final PaymentService paymentService;
    private final SseService sseService;

    @GetMapping("/freelancer/{id}")
    public List<PaymentEntity> getByFreelancer(@PathVariable Long id) {
        return paymentService.getPaymentsByFreelancer(id);
    }

    @PostMapping("/request")
    public PaymentEntity requestPayment(@RequestBody PaymentEntity p) {
        return paymentService.requestPayment(p);
    }

    @GetMapping("/stream/{freelancerId}")
    public SseEmitter streamPayments(@PathVariable Long freelancerId) {
        SseEmitter emitter = sseService.createEmitter(freelancerId);
        return emitter;
    }
}
	