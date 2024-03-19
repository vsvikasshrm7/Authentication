package com.example.Authentication.Service;

import java.util.List;
import java.util.Optional;

import com.example.Authentication.DTO.SendEmailMessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Authentication.DTO.UserDto;
import com.example.Authentication.Models.*;
import com.example.Authentication.kafkaClient.*;

import com.example.Authentication.Repository.RoleRepository;
import com.example.Authentication.Repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    
    @Autowired
    UserService(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserDto getUserDetails(Long id){
//        Optional<User> user =  userRepository.findById(id);
//        return UserDto.UserDtoFromUser(user.get());
        System.out.print("I got the request");
        return null;
    }

    public UserDto setUserRole(List<Long> roleId, Long id){
        Optional<User> user = userRepository.findById(id);
        List<Role> role = roleRepository.findAllById(roleId);

         User u = user.get();
         u.setRole(role);
        User saveUser =  userRepository.save(u);
         return UserDto.UserDtoFromUser(saveUser);
    }

//    public UserDto signup(String email, String password)  {
//        User user = new User();
//        user.setEmail(email);
//        user.setPassword(password);
//        User savedUser = userRepository.save(user);
//        UserDto userDto = UserDto.UserDtoFromUser(savedUser);
//        SendEmailMessageDTO sendEmailMessageDTO = new SendEmailMessageDTO();
//        sendEmailMessageDTO.setTo(user.getEmail());
//        sendEmailMessageDTO.setFrom("Admin@scaler.com");
//        sendEmailMessageDTO.setSubject("Welcome to scaler");
//        sendEmailMessageDTO.setBody("Thanks for creating account");
//        //We will be using Object mapper for sending serialized form of userDTo
//        try{
//            kafka_ProducerClient.sendMessage("UserSignUp", objectMapper.writeValueAsString(userDto));
//            kafka_ProducerClient.sendMessage("EmailService", objectMapper.writeValueAsString(sendEmailMessageDTO));
//        }
//        catch (Exception e){
//            System.out.print("Exception Handled");
//        }
//
//        return userDto;
//    }

}
