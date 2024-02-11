package com.swapvista.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ProductOffer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
    
    @ManyToOne
    @JoinColumn(name ="productId")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name ="userId")
    private User user;   // buyer
    
    private BigDecimal amount;
    
    private String dateTime;
    
    private String status;
    
}
