package com.swapvista.service;

import java.util.List;

import com.swapvista.entity.Orders;
import com.swapvista.entity.User;

public interface OrderService {

	//Adds one or more orders to the system
	List<Orders> addOrder(List<Orders> orders);
    
	//Updates one or more existing orders in the system
	List<Orders> updateOrders(List<Orders> orders);
    
	//Retrieves orders based on their order ID
	List<Orders> getOrdersByOrderId(String orderId);
    
	//Retrieves a single order based on its unique ID
	Orders getOrderById(int orderId);
    
	//Retrieves orders associated with a specific user
	List<Orders> getOrdersByUser(User user);
    
	//Updates a single order
	Orders updateOrder(Orders order);
    
	//Retrieves orders based on order ID and a list of statuses
	List<Orders> getOrdersByOrderIdAndStatusIn(String orderId, List<String> status);
	
	 //Retrieves orders for a specific user and a list of statuses
	List<Orders> getOrdersByUserAndStatusIn(User user, List<String> status);
	
	 //Retrieves orders for a specific seller and a list of statuses
	List<Orders> getOrdersBySellerAndStatusIn(User user, List<String> status);
	
	//Retrieves all orders in the system
	List<Orders> getAllOrders();
	
	

}
