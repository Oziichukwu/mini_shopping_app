package com.example.minishoppingoutlet.services;

import com.example.minishoppingoutlet.data.dtos.request.CreateUserRequest;
import com.example.minishoppingoutlet.data.dtos.response.CreateUserResponse;
import com.example.minishoppingoutlet.data.models.User;
import com.example.minishoppingoutlet.data.repositories.UserRepository;
import com.example.minishoppingoutlet.exceptions.EmailNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public CreateUserResponse register(CreateUserRequest createUserRequest) {

        if (userRepository.existsByEmail(createUserRequest.getEmail())){
            throw new EmailNotFoundException("User with email does not exist");
        }
        User user = modelMapper.map(createUserRequest, User.class);
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));

        User savedUser = save(user);

        return modelMapper.map(savedUser, CreateUserResponse.class);
    }

    private User save(User user) {
        return userRepository.save(user);
    }

}
