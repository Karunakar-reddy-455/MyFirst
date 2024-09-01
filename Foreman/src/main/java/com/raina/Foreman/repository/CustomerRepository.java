package com.raina.Foreman.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raina.Foreman.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	 Optional<Customer> findByUsername(String username);
	
}
