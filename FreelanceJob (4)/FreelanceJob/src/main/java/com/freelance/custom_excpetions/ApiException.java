package com.freelance.custom_excpetions;

public class ApiException extends RuntimeException {
	
	public ApiException (String mesg) {
		super(mesg);
	}

}
