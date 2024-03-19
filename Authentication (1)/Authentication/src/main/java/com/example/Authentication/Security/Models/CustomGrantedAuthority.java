package com.example.Authentication.Security.Models;

import com.example.Authentication.Models.Role;
import org.springframework.security.core.GrantedAuthority;

public class CustomGrantedAuthority implements GrantedAuthority {

    public Role role;
    CustomGrantedAuthority(Role role){
        this.role = role;
    }
    @Override
    public String getAuthority() {
        return role.getName();
    }
}
