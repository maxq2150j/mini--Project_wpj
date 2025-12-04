package com.freelance.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientproposals")
@Getter
@Setter
public class ClientProposalEntity  extends BaseEntity {
	
	private Long jobId;
    private Long freelancerId;
    private Double amount;
    
    private Long clientId;


    @Column(length = 2000)
    private String message;

    @Column(length = 20)
    private String status; 
	
	

}
