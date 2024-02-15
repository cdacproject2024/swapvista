package com.swapvista.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapvista.dto.OrderResponseDto;
import com.swapvista.resource.OrderResource;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
	
	@Autowired
	private OrderResource orderResource;
	
	@GetMapping("/fetch/all")
	@Operation(summary = "Api to fetch all orders")
	public ResponseEntity<OrderResponseDto> fetchAllOrders() {
		return orderResource.fetchAllOrders();
	}
	
	@GetMapping("/fetch/user-wise")
	@Operation(summary = "Api to fetch user orders")
	public ResponseEntity<OrderResponseDto> fetchUserOrders(@RequestParam("userId") int userId) {
		return orderResource.fetchUserOrders(userId);
	}
	
	@GetMapping("/fetch/seller-wise")
	@Operation(summary = "Api to fetch seller orders")
	public ResponseEntity<OrderResponseDto> fetchSellerOrders(@RequestParam("sellerId") int sellerId) {
		return orderResource.fetchSellerOrders(sellerId);
	}
	
	@GetMapping("/fetch")
	@Operation(summary = "Api to fetch orders by order id")
	public ResponseEntity<OrderResponseDto> fetchOrdersByOrderId(@RequestParam("orderId") String orderId) {
		return orderResource.fetchOrdersByOrderId(orderId);
	}
	
}
