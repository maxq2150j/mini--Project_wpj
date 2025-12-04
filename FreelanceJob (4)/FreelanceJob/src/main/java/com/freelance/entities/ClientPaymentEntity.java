package com.freelance.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientpayments")
@Getter
@Setter
public class ClientPaymentEntity extends BaseEntity {
	
	private Long taskId;
    private Long freelancerId;
    private Long payerId;
    private Double amount;

    @Column(length = 20)
    private String status;

}
