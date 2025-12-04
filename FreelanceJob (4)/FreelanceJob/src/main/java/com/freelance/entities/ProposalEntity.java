package com.freelance.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "proposals")
@Getter
@Setter
@ToString(callSuper = true)
public class ProposalEntity extends BaseEntity {
	
	
	  @Column(nullable = false)
	    private Double price;

	    @Column(length = 1000)
	    private String message;

	    @Column(length = 30)
	    private String status;

	    @Column(name = "freelancer_id")
	    private Long freelancerId;

	    @Column(name = "job_id")
	    private Long jobId;

	

}
