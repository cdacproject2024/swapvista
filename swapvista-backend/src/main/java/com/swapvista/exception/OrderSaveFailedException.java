package com.swapvista.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Define a custom exception to be thrown when order saving fails
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) 
public class OrderSaveFailedException extends RuntimeException {

	//Serial version UID for serialization
	private static final long serialVersionUID = 1L;

	//Constructor to create an instance of the exception with a custom message
	public OrderSaveFailedException(String message) {
		super(message);
	}

}
