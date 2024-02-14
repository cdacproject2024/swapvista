package com.swapvista.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ProductSaveFailedException extends RuntimeException {
	

	/**
	 * Exception thrown when there is a failure while saving a product.
	 */
	private static final long serialVersionUID = 1L;
	
	public ProductSaveFailedException(String message) {
		super(message);
	}

}
