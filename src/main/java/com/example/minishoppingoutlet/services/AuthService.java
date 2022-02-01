package com.example.minishoppingoutlet.services;

import com.example.minishoppingoutlet.data.dtos.request.CreateUserRequest;
import com.example.minishoppingoutlet.data.dtos.request.LoginRequest;
import com.example.minishoppingoutlet.data.dtos.request.PasswordUpdateRequest;
import com.example.minishoppingoutlet.data.dtos.request.PasswordResetRequest;
import com.example.minishoppingoutlet.data.dtos.response.CreateUserResponse;
import com.example.minishoppingoutlet.data.dtos.response.JwtResponseToken;
import com.example.minishoppingoutlet.data.models.Token;
import com.example.minishoppingoutlet.exceptions.AuthenticationException;
import com.example.minishoppingoutlet.exceptions.TokenException;


public interface AuthService {

    CreateUserResponse register(CreateUserRequest createUserRequest);

    JwtResponseToken login(LoginRequest loginRequest);

    void updatePassword(PasswordUpdateRequest passwordUpdateRequest) throws AuthenticationException;

    void resetUserPassword(PasswordResetRequest request, String passwordResetToken) throws AuthenticationException, TokenException;

    Token generatePasswordResetToken(String username) throws AuthenticationException;

}
