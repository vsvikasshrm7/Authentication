package com.example.Authentication.DTO;


import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.example.Authentication.Models.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String email;
    private List<Role> role;

    public static UserDto UserDtoFromUser(com.example.Authentication.Models.User user){
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        return userDto;        
    }
}
