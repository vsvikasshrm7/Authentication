package com.example.Authentication.Service;

import java.util.Date;
import java.util.Optional;

import com.auth0.jwt.JWT;
import com.example.Authentication.DTO.*;
import com.example.Authentication.Exception.EmptyToken;
import com.example.Authentication.Models.Session;
import com.example.Authentication.Models.SessionStatus;
import com.example.Authentication.kafkaClient.kafkaProducerClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Authentication.Exception.InvalidPasswordException;
import com.example.Authentication.Exception.UserAlreadyExistException;
import com.example.Authentication.Exception.UserNotFoundException;
import com.example.Authentication.Models.User;
import com.example.Authentication.Repository.RoleRepository;
import com.example.Authentication.Repository.SessionRepository;
import com.example.Authentication.Repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class AuthenticationService {
    
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private SessionRepository sessionRepository;
    
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private kafkaProducerClient kafka_ProducerClient;
    private ObjectMapper objectMapper;
    
    @Autowired
    AuthenticationService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, RoleRepository roleRepository, SessionRepository sessionRepository ,kafkaProducerClient kafka_ProducerClient, ObjectMapper objectMapper){
         this.roleRepository = roleRepository;
         this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
         this.sessionRepository= sessionRepository;
         this.userRepository = userRepository;
        this.kafka_ProducerClient = kafka_ProducerClient;
        this.objectMapper = objectMapper;
    }

    public ResponseEntity<UserDto> signup(SignUpRequestDto signUpRequestDto) throws UserAlreadyExistException{
        Optional<User> useroOptional = userRepository.findByEmail(signUpRequestDto.getEmail());
//        if(useroOptional.get() !=null){
//            throw new UserAlreadyExistException("User Alraedy Exist");
//        }
        User user = new User();
        user.setEmail(signUpRequestDto.getEmail());
        user.setName(signUpRequestDto.getName());
        user.setPassword(bCryptPasswordEncoder.encode(signUpRequestDto.getPassword()));
        User savedUser = userRepository.save(user);
        SendEmailMessageDTO sendEmailMessageDTO = new SendEmailMessageDTO();
        sendEmailMessageDTO.setTo(user.getEmail());
        sendEmailMessageDTO.setFrom("Admin@scaler.com");
        sendEmailMessageDTO.setSubject("Welcome to scaler");
        sendEmailMessageDTO.setBody("Thanks for creating account");
        //We will be using Object mapper for sending serialized form of userDTo
        try{
            kafka_ProducerClient.sendMessage("UserSignUp", objectMapper.writeValueAsString(UserDto.UserDtoFromUser(savedUser)));
            kafka_ProducerClient.sendMessage("EmailService", objectMapper.writeValueAsString(sendEmailMessageDTO));
        }
        catch (Exception e){
            System.out.print("Exception Handled");
        }


        return new ResponseEntity<>(UserDto.UserDtoFromUser(savedUser), HttpStatus.OK);
    }
     
    public ResponseEntity<UserDto> login(LoginRequestDto loginRequestDto) throws UserNotFoundException, InvalidPasswordException{

        Optional<User> optionaluser = userRepository.findByEmail(loginRequestDto.getEmail());
        if(optionaluser.get()==null){
            throw new UserNotFoundException("Invalid User Email");
        }
        
        User user = optionaluser.get();
        //Here match should be used
//        if(!bCryptPasswordEncoder.encode(loginRequestDto.getPassword()).equals(user.getPassword())){
//            throw new InvalidPasswordException("Wrong Password");
//        }
        if(!bCryptPasswordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            throw new InvalidPasswordException("Wrong Password");
        }
       //We are creating random string for JWT will do later
        Session session = new Session();
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setToken("RandomString or JWT");
        session.setUser(user);
        Session savedSession = sessionRepository.save(session);

        return new ResponseEntity<>(UserDto.UserDtoFromUser(optionaluser.get()), HttpStatus.OK);
    }
    public ResponseEntity<Void> logOut(LogOutRequestDto logOutRequestDto){
        Optional<Session> optionalSession = sessionRepository.findByToken(logOutRequestDto.getToken());
        if(optionalSession.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Session session = optionalSession.get();
        session.setSessionStatus(SessionStatus.EXPIRED);
        sessionRepository.save(session);
       // return ResponseEntity.ok().build()
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public SessionStatus validate(String token) throws EmptyToken{
         Optional<Session> optionalSession = sessionRepository.findByToken(token);
         if(optionalSession.isEmpty()){
             throw new EmptyToken("Token Not Found");
         }
         if(optionalSession.get().getSessionStatus().equals(SessionStatus.EXPIRED)){
             return SessionStatus.EXPIRED;
         }
//         if(optionalSession.get().getExpiringAt() < new Date()){
//             return SessionStatus.EXPIRED;
//         }

         return SessionStatus.ACTIVE;
    }

}
