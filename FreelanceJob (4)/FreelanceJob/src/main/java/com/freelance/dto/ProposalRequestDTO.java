package com.freelance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProposalRequestDTO {
	
	private Long jobId;
	private Long freelancerId;
	private Double price;
	private String message;
    private String status;

}
