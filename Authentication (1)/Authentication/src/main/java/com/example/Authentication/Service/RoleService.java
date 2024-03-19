package com.example.Authentication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Authentication.Models.Role;
import com.example.Authentication.Repository.RoleRepository;

@Service
public class RoleService {
    
    private RoleRepository roleRepository;

    @Autowired
    RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public Role createRole(String name){
        Role role = new Role();
        role.setName(name);
        return roleRepository.save(role);
    }
}
