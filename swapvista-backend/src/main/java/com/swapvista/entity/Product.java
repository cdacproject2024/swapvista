package com.swapvista.entity;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Product 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String description;
	
	@Column(name="end_date")
	private String endDate;
	
	private String image1;
	
	private String image2;
	
	private String image3;
	
	private String name;
	
	private BigDecimal price;
	
	private int quantity;
	
	private String status;
	
	@ManyToOne
	@JoinColumn(name="buyer_user_id")
	private User buyer;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="seller_user_id")
	private User Seller;

}
