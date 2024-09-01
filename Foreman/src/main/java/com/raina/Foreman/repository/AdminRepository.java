package com.raina.Foreman.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raina.Foreman.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
 

}
