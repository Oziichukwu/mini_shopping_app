package com.example.minishoppingoutlet.controller;


import com.example.minishoppingoutlet.data.dtos.request.CreateUserRequest;
import com.example.minishoppingoutlet.data.dtos.request.LoginRequest;
import com.example.minishoppingoutlet.data.dtos.response.ApiResponse;
import com.example.minishoppingoutlet.data.dtos.response.CreateUserResponse;
import com.example.minishoppingoutlet.data.dtos.response.JwtResponseToken;
import com.example.minishoppingoutlet.exceptions.EmailNotFoundException;
import com.example.minishoppingoutlet.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/miniStore/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register (@Valid @RequestBody CreateUserRequest createUserRequest){

        try {
            CreateUserResponse userResponse = authService.register(createUserRequest);
            return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
        }catch (EmailNotFoundException e){
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody  LoginRequest loginRequest){
        try{
            JwtResponseToken jwtResponseToken = authService.login(loginRequest);
            return new ResponseEntity<>(jwtResponseToken, HttpStatus.OK);
        }catch (EmailNotFoundException e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
