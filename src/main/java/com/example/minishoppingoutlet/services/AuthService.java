package com.example.minishoppingoutlet.services;

import com.example.minishoppingoutlet.data.dtos.request.CreateUserRequest;
import com.example.minishoppingoutlet.data.dtos.response.CreateUserResponse;

public interface AuthService {

    CreateUserResponse register(CreateUserRequest createUserRequest);

}
