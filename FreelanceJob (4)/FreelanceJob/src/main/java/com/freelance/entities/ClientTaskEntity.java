package com.freelance.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clienttasks")
@Getter
@Setter
public class ClientTaskEntity extends BaseEntity {
	
	private Long jobId;
	private Long proposalId;

    private Long clientId;
    private Long freelancerId;
    private Double amount;

    private String status; 
    private boolean paid;


}
