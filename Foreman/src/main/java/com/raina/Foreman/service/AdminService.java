package com.raina.Foreman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.raina.Foreman.dto.AdminDto;
import com.raina.Foreman.entity.Admin;
import com.raina.Foreman.repository.AdminRepository;


public class AdminService {

	@Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Admin registerAdmin(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setUsername(adminDto.getUsername());
        admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        admin.setSuperAdmin(adminDto.isSuperAdmin());
        return adminRepository.save(admin);
    }
    public List<Admin> getAllAdminsDetails() {
        return adminRepository.findAll();
    }

    public Admin getAdminByUsername(String username) {
        return adminRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Admin not found"));
    }
	
}
