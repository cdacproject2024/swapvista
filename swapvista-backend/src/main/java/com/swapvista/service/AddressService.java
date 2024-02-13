package com.swapvista.service;

import java.util.List;

import com.swapvista.entity.Address;
import com.swapvista.entity.User;

public interface AddressService {
	
	Address addAddress(Address address);
	
	Address updateAddress(Address address);
	
	Address getAddressById(int addressId);

}
