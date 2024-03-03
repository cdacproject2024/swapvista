package com.swapvista.dto;

import org.springframework.beans.BeanUtils;

import com.swapvista.entity.User;

import lombok.Data;

@Data
public class RegisterUserRequestDto {
	
	private String firstName;

	private String lastName;

	private String emailId;

	private String password;

	private String phoneNo;

	private String role;
	
	private String street;

	private String city;

	private int pincode;  
	
	// Converts RegisterUserRequestDto to User entity
	public static User toUserEntity(RegisterUserRequestDto registerUserRequestDto) {
		User user =new User();
		
    // Copies properties from RegisterUserRequestDto to User entity
	BeanUtils.copyProperties(registerUserRequestDto, user);		
		return user;
	}
	
}
