package com.freelance.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {
	
	
	private String message;
	private String status;
	
	
	public ApiResponse(String message, String status) {
		super();
		this.message = message;
		this.status = status;
	}
}
