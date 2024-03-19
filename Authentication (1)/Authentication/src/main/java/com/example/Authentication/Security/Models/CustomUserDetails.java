//package com.example.Authentication.Security.Models;
//
//import com.example.Authentication.Models.Role;
//import com.example.Authentication.Models.User;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import lombok.NoArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@JsonDeserialize
//@NoArgsConstructor
//@Component
//public class CustomUserDetails implements UserDetails {
//    //How it will know that user object is to be injected
//    private com.example.Authentication.Models.User user;
//    CustomUserDetails(User user){
//       this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<CustomGrantedAuthority> customGrantedAuthorities = new ArrayList<>();
//        for(Role role : user.getRole()){
//
//            customGrantedAuthorities.add(new CustomGrantedAuthority(role));
//        }
//        return customGrantedAuthorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getName();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
