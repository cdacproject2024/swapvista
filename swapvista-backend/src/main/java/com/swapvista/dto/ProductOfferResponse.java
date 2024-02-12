package com.swapvista.dto;

import java.util.ArrayList;
import java.util.List;

import com.swapvista.entity.ProductOffer;
import lombok.Data;


//Data Transfer Object (DTO) representing the response containing a list of ProductOffers.
@Data
public class ProductOfferResponse extends CommonApiResponse {
	
	// List of ProductOffers included in the response.
	List<ProductOffer> offers = new ArrayList<>();

}
