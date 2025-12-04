package com.freelance.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "job")
@Getter
@Setter
@NoArgsConstructor
public class JobEntity  extends BaseEntity {
	
	
	@Column(length = 50, nullable = false)
	private String title;
	
	@Column(length = 1000)
	private String description;
	
	@Column(length = 20)
	private String budget; 
	
	public JobEntity(String title, String description, String budget) {
		super();
		this.title = title;
		this.description = description;
		this.budget = budget;
	}
	

}
