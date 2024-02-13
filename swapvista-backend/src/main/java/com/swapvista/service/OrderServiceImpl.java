package com.swapvista.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapvista.dao.OrdersDao;
import com.swapvista.entity.Orders;
import com.swapvista.entity.User;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	//Injecting dependency of OrdersDao for data access
	private OrdersDao orderDao;

	//Adds orders to the system
	@Override
	public List<Orders> addOrder(List<Orders> orders) {
		return this.orderDao.saveAll(orders);
	}
 
	//Updates one or more existing orders in the system
	@Override
	public List<Orders> updateOrders(List<Orders> orders) {
		return this.orderDao.saveAll(orders);
	}

	//Retrieves orders based on their order ID
	@Override
	public List<Orders> getOrdersByOrderId(String orderId) {

		return this.orderDao.findByOrderId(orderId);

	}
	//Retrieves a single order based on its unique ID
	@Override
	public Orders getOrderById(int orderId) {

		Optional<Orders> optionalOrder = this.orderDao.findById(orderId);

		//Return the order if present, otherwise return null
		if (optionalOrder.isPresent()) {
			return optionalOrder.get();
		} else {
			return null;
		}

	}

	//Retrieves orders associated with a specific user
	@Override
	public List<Orders> getOrdersByUser(User user) {
		return orderDao.findByUser(user);
	}

	//Updates a single order
	@Override
	public Orders updateOrder(Orders order) {
		return orderDao.save(order);
	}

	//Retrieves orders based on order ID and a list of statuses
	@Override
	public List<Orders> getOrdersByOrderIdAndStatusIn(String orderId, List<String> status) {
		return this.orderDao.findByOrderIdAndStatusIn(orderId, status);
	}
	
	//Retrieves orders for a specific user and a list of statuses
	@Override
	public List<Orders> getOrdersByUserAndStatusIn(User user, List<String> status) {
		return this.orderDao.findByUserAndStatusIn(user, status);
	}

	//Retrieves orders for a specific seller and a list of statuses
	@Override
	public List<Orders> getOrdersBySellerAndStatusIn(User user, List<String> status) {
		return this.orderDao.findAllOrdersBySellerAndStatusIn(user, status);
	}

	//Retrieves all orders in the system
	@Override
	public List<Orders> getAllOrders() {
		return this.orderDao.findAll();
	}


}
