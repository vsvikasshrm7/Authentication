package com.example.Authentication.Security.Services;

import com.example.Authentication.Models.User;
import com.example.Authentication.Repository.UserRepository;
import com.example.Authentication.Security.Models.CustomGrantedAuthority;

import com.nimbusds.oauth2.sdk.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

     CustomUserDetailsService(UserRepository userRepository){
         this.userRepository = userRepository;
     }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User>  user1 = userRepository.findByEmail(username);
        if(user1.isEmpty()){
            throw new UsernameNotFoundException("User Not Found");
        }
        //org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(user1.get().getEmail(), user1.get().getPassword() );
        return null;
    }
}
