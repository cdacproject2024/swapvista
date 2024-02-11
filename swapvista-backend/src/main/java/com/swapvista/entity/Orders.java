package com.swapvista.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String orderId;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	@ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

	private int quantity;
	
	private String orderTime;
	
	private String status;  // Processing, Delivered, On the Way // this will be for customer

	@OneToOne
	@JoinColumn(name = "productOfferId")
	private ProductOffer productOffer;

}