package com.swapvista.service;

import java.util.List;

import com.swapvista.entity.Product;
import com.swapvista.entity.ProductOffer;
import com.swapvista.entity.User;

public interface ProductOfferService {
	
//	* Adds a new offer to the system.
	
	ProductOffer addOffer(ProductOffer offer);
	
	
//	* Updates an existing offer in the system.
	
	ProductOffer updateOffer(ProductOffer offer);
	
	
	
	ProductOffer getOfferById(int offerId);
	
//	 Retrieves offers made by a specific user with the specified status.
	 
	List<ProductOffer> getOffersByUserAndStatus(User user, List<String> status);
	 
//	 * Retrieves offers for a specific product with the specified status.
	List<ProductOffer> getOffersByProductAndStatus(Product product, List<String> status);
	

}
