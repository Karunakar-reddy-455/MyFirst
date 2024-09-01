package com.raina.Foreman.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.raina.Foreman.dto.AdminDto;
import com.raina.Foreman.dto.CustomerDto;
import com.raina.Foreman.entity.Admin;
import com.raina.Foreman.entity.Customer;
import com.raina.Foreman.service.AdminService;
import com.raina.Foreman.service.CustomerService;
import com.raina.Foreman.util.JwtUtil;

public class AuthController {

	
	@Autowired
    private AdminService adminService;

    @Autowired
    private CustomerService customerService;
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/admin/register")
    public String registerAdmin(@RequestBody AdminDto adminDto) {
        adminService.registerAdmin(adminDto);
        return "Admin registered successfully!";
    }

    @PostMapping("/customer/register")
    public String registerCustomer(@RequestBody CustomerDto customerDto) {
        customerService.registerCustomer(customerDto);
        return "Customer registered successfully!";
    }
        
    @GetMapping("getallAdminsData")
	public ResponseEntity<List<Admin>> getAllAdmins() {
		List<Admin> admin = adminService.getAllAdminsDetails();
		return new ResponseEntity<>(admin, HttpStatus.OK);
    }
    
    public ResponseEntity<List<Customer>> getAllCustomers(){
    	List<Customer> customer = customerService.getAllCustomersDetails();
    	return new ResponseEntity<List<Customer>>(customer,HttpStatus.OK);
    	
    }

    @PostMapping("/admin/login")
    public String loginAdmin(@RequestBody AdminDto adminDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminDto.getUsername(), adminDto.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(adminDto.getUsername());
        return jwtUtil.generateToken(userDetails);
    }

    @PostMapping("/customer/login")
    public String loginCustomer(@RequestBody CustomerDto customerDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customerDto.getUsername(), customerDto.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(customerDto.getUsername());
        return jwtUtil.generateToken(userDetails);
    }
	
}
