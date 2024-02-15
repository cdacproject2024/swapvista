package com.swapvista.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapvista.dto.CommonApiResponse;
import com.swapvista.dto.ProductOfferRequestDto;
import com.swapvista.dto.ProductOfferResponse;
import com.swapvista.resource.ProductOfferResource;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/product/offer")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductOfferController {

	@Autowired
	private ProductOfferResource productOfferResource;

	@PostMapping("add")
	@Operation(summary = "Api to add product offer")
	public ResponseEntity<CommonApiResponse> addOffer(@RequestBody ProductOfferRequestDto request) {
		return this.productOfferResource.addProductOffer(request);
	}
	/**
	 * Endpoint to fetch product offers by product ID.
	 * @param productId The ID of the product for which offers are to be fetched.
	 * @return ResponseEntity with a ProductOfferResponse containing the product offers.
	 */
	@GetMapping("fetch/product")
	@Operation(summary = "Api to fetch product offers by product")
	public ResponseEntity<ProductOfferResponse> fetchProductOffersByProduct(@RequestParam("productId") int productId) {
		return this.productOfferResource.fetchProductOffersByProduct(productId);
	}
	/**
	 * Endpoint to fetch product offers by user ID.
	 * @param userId The ID of the user for whom offers are to be fetched.
	 * @return ResponseEntity with the product offers made by the user.
	 */
	@GetMapping("fetch/user")
	@Operation(summary = "Api to add product offers by user")
	public ResponseEntity<?> fetchProductOffersByUser(@RequestParam("userId") int userId) {
		return this.productOfferResource.fetchProductOffersByUser(userId);
	}

	@DeleteMapping("/id")
	@Operation(summary = "Api to delete the product offer")
	public ResponseEntity<CommonApiResponse> deleteOffer(@RequestParam("offerId") int offerId) {
		return this.productOfferResource.deleteOffer(offerId);
	}

}
