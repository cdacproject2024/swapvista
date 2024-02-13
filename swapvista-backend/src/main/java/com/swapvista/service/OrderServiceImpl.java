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
<<<<<<< Updated upstream
	//Injecting dependency of OrdersDao for data access
	private OrdersDao orderDao;

	//Adds orders to the system
=======
	private OrdersDao orderDao;

>>>>>>> Stashed changes
	@Override
	public List<Orders> addOrder(List<Orders> orders) {
		return this.orderDao.saveAll(orders);
	}
<<<<<<< Updated upstream
 
	//Updates one or more existing orders in the system
=======

>>>>>>> Stashed changes
	@Override
	public List<Orders> updateOrders(List<Orders> orders) {
		return this.orderDao.saveAll(orders);
	}

<<<<<<< Updated upstream
	//Retrieves orders based on their order ID
=======
>>>>>>> Stashed changes
	@Override
	public List<Orders> getOrdersByOrderId(String orderId) {

		return this.orderDao.findByOrderId(orderId);

	}
<<<<<<< Updated upstream
	//Retrieves a single order based on its unique ID
=======

>>>>>>> Stashed changes
	@Override
	public Orders getOrderById(int orderId) {

		Optional<Orders> optionalOrder = this.orderDao.findById(orderId);

<<<<<<< Updated upstream
		//Return the order if present, otherwise return null
=======
>>>>>>> Stashed changes
		if (optionalOrder.isPresent()) {
			return optionalOrder.get();
		} else {
			return null;
		}

	}

<<<<<<< Updated upstream
	//Retrieves orders associated with a specific user
=======
>>>>>>> Stashed changes
	@Override
	public List<Orders> getOrdersByUser(User user) {
		return orderDao.findByUser(user);
	}

<<<<<<< Updated upstream
	//Updates a single order
=======
>>>>>>> Stashed changes
	@Override
	public Orders updateOrder(Orders order) {
		return orderDao.save(order);
	}

<<<<<<< Updated upstream
	//Retrieves orders based on order ID and a list of statuses
=======
>>>>>>> Stashed changes
	@Override
	public List<Orders> getOrdersByOrderIdAndStatusIn(String orderId, List<String> status) {
		return this.orderDao.findByOrderIdAndStatusIn(orderId, status);
	}
<<<<<<< Updated upstream
	
	//Retrieves orders for a specific user and a list of statuses
=======

>>>>>>> Stashed changes
	@Override
	public List<Orders> getOrdersByUserAndStatusIn(User user, List<String> status) {
		return this.orderDao.findByUserAndStatusIn(user, status);
	}

<<<<<<< Updated upstream
	//Retrieves orders for a specific seller and a list of statuses
=======
>>>>>>> Stashed changes
	@Override
	public List<Orders> getOrdersBySellerAndStatusIn(User user, List<String> status) {
		return this.orderDao.findAllOrdersBySellerAndStatusIn(user, status);
	}

<<<<<<< Updated upstream
	//Retrieves all orders in the system
=======
>>>>>>> Stashed changes
	@Override
	public List<Orders> getAllOrders() {
		return this.orderDao.findAll();
	}


}
