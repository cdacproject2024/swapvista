package com.swapvista.resource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.swapvista.dto.CommonApiResponse;
import com.swapvista.dto.ProductOfferRequestDto;
import com.swapvista.dto.ProductOfferResponse;
import com.swapvista.entity.Product;
import com.swapvista.entity.ProductOffer;
import com.swapvista.entity.User;
import com.swapvista.service.ProductOfferService;
import com.swapvista.service.ProductService;
import com.swapvista.service.UserService;
import com.swapvista.utility.Constants.ProductOfferStatus;

@Component
public class ProductOfferResource {

	private final Logger LOG = LoggerFactory.getLogger(ProductOfferResource.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductOfferService productOfferService;
	 // Method for adding a product offer based on the provided request data.
	public ResponseEntity<CommonApiResponse> addProductOffer(ProductOfferRequestDto request) {

		LOG.info("request received for adding product offer");

		CommonApiResponse response = new CommonApiResponse();
		
		  // Validate request data
		if (request == null || request.getUserId() == 0 || request.getProductId() == 0
				|| request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
			response.setResponseMessage("Bad Request, Invalid Request Data");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}
		 // Retrieve product based on product ID
		Product product = this.productService.getProductById(request.getProductId());

		if (product == null) {
			response.setResponseMessage("Product not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		User user = this.userService.getUserById(request.getUserId());

		if (user == null) {
			response.setResponseMessage("User not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if (product.getPrice().compareTo(request.getAmount()) > 0) {
			response.setResponseMessage("Offer Amount can't be less than Product Min Amount!!!");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		if (user.getWalletAmount().compareTo(request.getAmount()) < 0) {
			response.setResponseMessage("Insufficient Fund in Wallet to add the Offer!!!");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		String requestTime = String
				.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

		ProductOffer productOffer = new ProductOffer();
		productOffer.setAmount(request.getAmount());
		productOffer.setDateTime(requestTime);
		productOffer.setProduct(product);
		productOffer.setUser(user);
		productOffer.setStatus(ProductOfferStatus.ACTIVE.value());

		ProductOffer addedOffer = this.productOfferService.addOffer(productOffer);

		if (addedOffer == null) {
			response.setResponseMessage("Failed to add the offer!!!");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			response.setResponseMessage("Product Offer added succcessfully!!!");
			response.setSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
		}
	}

	public ResponseEntity<ProductOfferResponse> fetchProductOffersByProduct(int productId) {

		LOG.info("request received for fetching the Offers by product ids");

		ProductOfferResponse response = new ProductOfferResponse();

		if (productId == 0) {
			response.setResponseMessage("Bad Request, Product id missing");
			response.setSuccess(false);

			return new ResponseEntity<ProductOfferResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Product product = this.productService.getProductById(productId);

		if (product == null) {
			response.setResponseMessage("product not found");
			response.setSuccess(false);

			return new ResponseEntity<ProductOfferResponse>(response, HttpStatus.BAD_REQUEST);
		}

		List<ProductOffer> offers = this.productOfferService.getOffersByProductAndStatus(product, Arrays.asList(
				ProductOfferStatus.ACTIVE.value(), ProductOfferStatus.LOSE.value(), ProductOfferStatus.WIN.value()));

		if (CollectionUtils.isEmpty(offers)) {
			response.setResponseMessage("No prouct offers found");
			response.setSuccess(false);

			return new ResponseEntity<ProductOfferResponse>(response, HttpStatus.OK);
		}

		response.setOffers(offers);
		response.setResponseMessage("Offers fetched success");
		response.setSuccess(true);

		return new ResponseEntity<ProductOfferResponse>(response, HttpStatus.OK);

	}

	public ResponseEntity<?> fetchProductOffersByUser(int userId) {

		LOG.info("request received for fetching the Offers by user id");

		ProductOfferResponse response = new ProductOfferResponse();

		if (userId == 0) {
			response.setResponseMessage("Bad Request, user id missing");
			response.setSuccess(false);

			return new ResponseEntity<ProductOfferResponse>(response, HttpStatus.BAD_REQUEST);
		}

		User user = this.userService.getUserById(userId);

		if (user == null) {
			response.setResponseMessage("user not found");
			response.setSuccess(false);

			return new ResponseEntity<ProductOfferResponse>(response, HttpStatus.BAD_REQUEST);
		}

		List<ProductOffer> offers = this.productOfferService.getOffersByUserAndStatus(user,
				Arrays.asList(ProductOfferStatus.ACTIVE.value(), ProductOfferStatus.LOSE.value(),
						ProductOfferStatus.WIN.value(), ProductOfferStatus.CANCELLED.value()));

		if (CollectionUtils.isEmpty(offers)) {
			response.setResponseMessage("No offers found");
			response.setSuccess(false);

			return new ResponseEntity<ProductOfferResponse>(response, HttpStatus.OK);
		}

		response.setOffers(offers);
		response.setResponseMessage("Offers fetched success");
		response.setSuccess(true);

		return new ResponseEntity<ProductOfferResponse>(response, HttpStatus.OK);

	}

	public ResponseEntity<CommonApiResponse> deleteOffer(int offerId) {

		LOG.info("request received for deleting product offer");

		CommonApiResponse response = new CommonApiResponse();

		if (offerId == 0) {
			response.setResponseMessage("Bad Request, Offer Id missing");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		ProductOffer offer = this.productOfferService.getOfferById(offerId);

		if (offer == null) {
			response.setResponseMessage("Product Offer not found");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		offer.setStatus(ProductOfferStatus.CANCELLED.value());
		ProductOffer updatedOffer = this.productOfferService.updateOffer(offer);

		if (updatedOffer == null) {
			response.setResponseMessage("Failed to cancel the offer!!!");
			response.setSuccess(false);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			response.setResponseMessage("Product Offer cancelled succcessful!!!");
			response.setSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
		}
	}

}
