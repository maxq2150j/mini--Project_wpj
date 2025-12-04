package com.freelance.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientjobs")
@Getter
@Setter
public class ClientJobEntity extends BaseEntity {
	
	 private String title;

	    @Column(length = 2000)
	    private String description;

	    private Double budget;

	    private Long clientId;
	
	

}
