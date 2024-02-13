package com.swapvista.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapvista.entity.Address;
//provides CRUD operations for Address entity 
@Repository
public interface AddressDao extends JpaRepository<Address, Integer> {

}
