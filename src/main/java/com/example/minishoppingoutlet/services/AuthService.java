package com.example.minishoppingoutlet.services;

import com.example.minishoppingoutlet.data.dtos.request.CreateUserRequest;
import com.example.minishoppingoutlet.data.dtos.request.LoginRequest;
import com.example.minishoppingoutlet.data.dtos.response.CreateUserResponse;
import com.example.minishoppingoutlet.data.dtos.response.JwtResponseToken;

import javax.security.auth.message.AuthException;

public interface AuthService {

    CreateUserResponse register(CreateUserRequest createUserRequest);

    JwtResponseToken login(LoginRequest loginRequest);

}
