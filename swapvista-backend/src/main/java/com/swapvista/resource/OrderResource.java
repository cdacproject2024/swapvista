package com.swapvista.resource;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.swapvista.dto.OrderResponseDto;
import com.swapvista.entity.Orders;
import com.swapvista.entity.User;
import com.swapvista.service.OrderService;
import com.swapvista.service.UserService;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class OrderResource {

	private final Logger LOG = LoggerFactory.getLogger(OrderResource.class);

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;


	// Fetches all orders and returns them in a response.
	public ResponseEntity<OrderResponseDto> fetchAllOrders() {

		LOG.info("Request received for fetching all orders");

		OrderResponseDto response = new OrderResponseDto();

		List<Orders> orders = new ArrayList<>();
		//Call the order service to get all orders
		orders = this.orderService.getAllOrders();

		//Check if any orders were found
		if (CollectionUtils.isEmpty(orders)) {
			response.setResponseMessage("No orders found");
			response.setSuccess(false);

			return new ResponseEntity<OrderResponseDto>(response, HttpStatus.BAD_REQUEST);
		}
		//Set the orders and response message in the response object
		response.setOrders(orders);
		response.setResponseMessage("Orders fetched successful");
		response.setSuccess(true);
        
		 //Return the response with a status code of OK
		return new ResponseEntity<OrderResponseDto>(response, HttpStatus.OK);
	}

	//Fetches all orders placed by a specific user and returns them in a response.
	public ResponseEntity<OrderResponseDto> fetchUserOrders(int userId) {

		LOG.info("Request received for fetching all orders");

		OrderResponseDto response = new OrderResponseDto();

		//Check if the user ID is valid
		if (userId == 0) {
			response.setResponseMessage("User Id missing");
			response.setSuccess(false);

			return new ResponseEntity<OrderResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		//Get the user object from the user service
		User user = this.userService.getUserById(userId);

		 //Check if the user was found
		if (user == null) {
			response.setResponseMessage("User not found, failed to fetch user orders");
			response.setSuccess(false);

			return new ResponseEntity<OrderResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		List<Orders> orders = new ArrayList<>();


        //Check if any orders were found
		if (CollectionUtils.isEmpty(orders)) {
			response.setResponseMessage("No orders found");
			response.setSuccess(false);

			return new ResponseEntity<OrderResponseDto>(response, HttpStatus.OK);
		}

		//Set the orders and response message in the response object
		response.setOrders(orders);
		response.setResponseMessage("Orders fetched successful");
		response.setSuccess(true);

		// Return the response with a status code of OK
		return new ResponseEntity<OrderResponseDto>(response, HttpStatus.OK);
	}

	// Method to fetch orders for a particular seller
	public ResponseEntity<OrderResponseDto> fetchSellerOrders(int sellerId) {

		//Log the request received
		LOG.info("Request received for fetching all seller orders");

		//Create a response object
		OrderResponseDto response = new OrderResponseDto();

		//Check if sellerId is missing
		if (sellerId == 0) {
			response.setResponseMessage("Seller Id missing");
			response.setSuccess(false);

			//Return a bad request response with the error message
			return new ResponseEntity<OrderResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		//Get seller information by sellerId
		User seller = this.userService.getUserById(sellerId);

		 //If seller not found, set error message and return bad request response
		if (seller == null) {
			response.setResponseMessage("Seller not found, failed to fetch seller orders");
			response.setSuccess(false);

			return new ResponseEntity<OrderResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		//Retrieve orders for the seller
		List<Orders> orders = new ArrayList<>();

		 //If no orders found, set error message and return OK response
		if (CollectionUtils.isEmpty(orders)) {
			response.setResponseMessage("No orders found");
			response.setSuccess(false);

			return new ResponseEntity<OrderResponseDto>(response, HttpStatus.OK);
		}

		//Set the fetched orders in the response object
		response.setOrders(orders);
		response.setResponseMessage("Orders fetched successful");
		response.setSuccess(true);

		//Return OK response with fetched orders
		return new ResponseEntity<OrderResponseDto>(response, HttpStatus.OK);
	}

	//Method to fetch orders by orderId
	public ResponseEntity<OrderResponseDto> fetchOrdersByOrderId(String orderId) {

		//Log the request received
		LOG.info("Request received for fetching all orders");

		//Create a response object
		OrderResponseDto response = new OrderResponseDto();

		//Check if orderId is missing
		if (orderId == null) {
			response.setResponseMessage("Order Id missing");
			response.setSuccess(true);

			//Return a bad request response with the error message
			return new ResponseEntity<OrderResponseDto>(response, HttpStatus.BAD_REQUEST);
		}

		//Retrieve orders by orderId
		List<Orders> orders = new ArrayList<>();

		orders = this.orderService.getOrdersByOrderId(orderId);

	    //If no orders found, set error message and return OK response
		if (CollectionUtils.isEmpty(orders)) {
			response.setResponseMessage("No orders found");
			response.setSuccess(false);

			return new ResponseEntity<OrderResponseDto>(response, HttpStatus.OK);
		}

		//Set the fetched orders in the response object
		response.setOrders(orders);
		response.setResponseMessage("Orders fetched successful");
		response.setSuccess(true);

		//Return OK response with fetched orders
		return new ResponseEntity<OrderResponseDto>(response, HttpStatus.OK);
	}

}
