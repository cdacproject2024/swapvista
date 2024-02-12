package com.swapvista.dto;

import java.math.BigDecimal;

import lombok.Data;



//Data Transfer Object (DTO) representing the request to create or update a ProductOffer.
@Data
public class ProductOfferRequestDto {
	
	

    // ID of the Product associated with the offer.
	private int productId;
	
	 // ID of the User making the offer.
	private int userId;
	
	   // Amount (price) offered for the product.
	private BigDecimal amount;


    // Date and time of the offer.
	private String dateTime;

}
