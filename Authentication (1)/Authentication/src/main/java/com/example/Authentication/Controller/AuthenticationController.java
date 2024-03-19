package com.example.Authentication.Controller;


import com.example.Authentication.DTO.*;
import com.example.Authentication.Exception.EmptyToken;
import com.example.Authentication.Exception.InvalidPasswordException;
import com.example.Authentication.Exception.UserAlreadyExistException;
import com.example.Authentication.Exception.UserNotFoundException;
import com.example.Authentication.Models.SessionStatus;
import com.example.Authentication.Service.AuthenticationService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private AuthenticationService authenticationService;
    @Autowired
    AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    ResponseEntity<UserDto> login(@Nullable @RequestBody LoginRequestDto loginRequestDto) throws InvalidPasswordException, UserNotFoundException {
        return authenticationService.login(loginRequestDto);
    }
    @PostMapping ("/signup")
    ResponseEntity<UserDto> signup(@RequestBody SignUpRequestDto signUpRequestDto) throws UserAlreadyExistException {
        return authenticationService.signup(signUpRequestDto);
    }

    @PostMapping("/logout")
    ResponseEntity<Void> logout(@RequestBody LogOutRequestDto logOutRequestDto){
       return authenticationService.logOut(logOutRequestDto);
    }

    @PostMapping("/validate")
    SessionStatus validate(@RequestBody ValidateTokenRequestDto validateTokenRequestDto) throws EmptyToken {
        return authenticationService.validate(validateTokenRequestDto.getToken());
    }
}
