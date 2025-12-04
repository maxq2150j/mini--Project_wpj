package com.freelance.entities;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "task")
@Getter
@Setter

public class TaskEntity extends BaseEntity {
	
	    private Long jobId;
	    private Long freelancerId;
	    private Double amount;

	    @Column(length = 20)
	    private String status; // assigned, completed

	    private Boolean paid = false;
	
	
	

}
