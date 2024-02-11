package com.swapvista.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapvista.entity.Product;
import com.swapvista.entity.ProductOffer;
import com.swapvista.entity.User;


@Repository
public interface ProductOfferDao extends JpaRepository<ProductOffer, Integer>
{
	
	List<ProductOffer> findByProductAndStatusIn(Product product, List<String> status);
	 // Method to retrieve a list of ProductOffers associated with a specific Product and having statuses within a provided list.
	List<ProductOffer> findByUserAndStatusIn(User user, List<String> status);
	   // Method to retrieve a list of ProductOffers created by a specific User and having statuses within a provided list.

}
