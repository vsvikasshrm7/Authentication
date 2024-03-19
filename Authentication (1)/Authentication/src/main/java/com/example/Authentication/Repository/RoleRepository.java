package com.example.Authentication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Authentication.Models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    
}
