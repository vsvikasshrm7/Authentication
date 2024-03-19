package com.example.Authentication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Authentication.DTO.UserDto;
import com.example.Authentication.Service.UserService;

@RestController
@RequestMapping("/authentication")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/get")
   public ResponseEntity<UserDto> getUserDetails(Long id){
        UserDto userDto = userService.getUserDetails(id);
        ResponseEntity<UserDto> responseEntity= new ResponseEntity<>(userDto, HttpStatus.OK);
        return responseEntity;
    }

//    @PostMapping()
//    public ResponseEntity<UserDto> signup(){
//        UserDto userDto = userService.signup();
//        ResponseEntity<UserDto> responseEntity = new ResponseEntity<UserDto>(userDto);
//        return responseEntity;
//    }

}
