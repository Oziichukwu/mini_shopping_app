package com.example.minishoppingoutlet.services;

import com.example.minishoppingoutlet.data.dtos.request.CreateUserRequest;
import com.example.minishoppingoutlet.data.dtos.request.LoginRequest;
import com.example.minishoppingoutlet.data.dtos.response.CreateUserResponse;
import com.example.minishoppingoutlet.data.dtos.response.JwtResponseToken;
import com.example.minishoppingoutlet.data.models.User;
import com.example.minishoppingoutlet.data.repositories.UserRepository;
import com.example.minishoppingoutlet.exceptions.EmailNotFoundException;
import com.example.minishoppingoutlet.security.CustomUserDetailService;
import com.example.minishoppingoutlet.security.JwtTokenProvider;
import com.example.minishoppingoutlet.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

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

    @Override
    public JwtResponseToken login(LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserPrincipal userDetails = (UserPrincipal) customUserDetailService.loadUserByUsername(loginRequest.getEmail());
        final String token = jwtTokenProvider.generateToken(userDetails);
        User user = internalFindUserByEmail(loginRequest.getEmail());
        return new JwtResponseToken(token , user.getEmail());
    }

    private User internalFindUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }


    private User save(User user) {
        return userRepository.save(user);
    }

}
