package com.raina.Foreman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.raina.Foreman.dto.CustomerDto;
import com.raina.Foreman.entity.Admin;
import com.raina.Foreman.entity.Customer;
import com.raina.Foreman.repository.AdminRepository;
import com.raina.Foreman.repository.CustomerRepository;

public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 public List<Customer> getAllCustomersDetails() {
	        return customerRepository.findAll();
	    }

	public Customer registerCustomer(CustomerDto customerDto) {
	    Customer customer = new Customer();
	    customer.setUsername(customerDto.getUsername());
	    customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
	    return customerRepository.save(customer);
	}
	
}
