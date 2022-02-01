package com.example.minishoppingoutlet.services;

import com.example.minishoppingoutlet.data.dtos.request.CreateUserRequest;
import com.example.minishoppingoutlet.data.dtos.request.LoginRequest;
import com.example.minishoppingoutlet.data.dtos.request.PasswordUpdateRequest;
import com.example.minishoppingoutlet.data.dtos.request.PasswordResetRequest;
import com.example.minishoppingoutlet.data.dtos.response.CreateUserResponse;
import com.example.minishoppingoutlet.data.dtos.response.JwtResponseToken;
import com.example.minishoppingoutlet.data.models.Token;
import com.example.minishoppingoutlet.data.models.User;
import com.example.minishoppingoutlet.data.repositories.TokenRepository;
import com.example.minishoppingoutlet.data.repositories.UserRepository;
import com.example.minishoppingoutlet.exceptions.AuthenticationException;
import com.example.minishoppingoutlet.exceptions.EmailNotFoundException;
import com.example.minishoppingoutlet.exceptions.TokenException;
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

import java.time.LocalDateTime;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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

    @Override
    public void updatePassword(PasswordUpdateRequest passwordUpdateRequest) throws AuthenticationException {

        String email = passwordUpdateRequest.getEmail();
        String oldPassword = passwordUpdateRequest.getOldPassword();
        String newPassword = passwordUpdateRequest.getPassword();

        User userToChangePassword = userRepository.findUserByEmail(email).orElseThrow(()->
                new AuthenticationException("user with password does not exist"));

        boolean passwordMatch = passwordEncoder.matches(oldPassword, userToChangePassword.getPassword());

        if (!passwordMatch){
            throw new AuthenticationException("Password does not match");
        }
        userToChangePassword.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(userToChangePassword);
    }

    @Override
    public void resetUserPassword(PasswordResetRequest request, String passwordResetToken) throws AuthenticationException, TokenException {

        String email = request.getEmail();
        String newPassword = request.getPassword();

        User userToResetPassword = userRepository.findUserByEmail(email).orElseThrow(()->
                new AuthenticationException("User with email does not exist"));

        Token token = tokenRepository.findByToken(passwordResetToken).orElseThrow(()->
                new TokenException("Token for this user does not exist"));

        if (token.getExpiryDate().isBefore(LocalDateTime.now())){
            throw new TokenException("This token has expired");
        }

        if (!token.getUser().getUserId().equals(userToResetPassword.getUserId())){
            throw new TokenException("Token does not match the user");
        }

        userToResetPassword.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(userToResetPassword);
        tokenRepository.delete(token);

    }

    @Override
    public Token generatePasswordResetToken(String username) throws AuthenticationException {
        return null;
    }

    private User internalFindUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }


    private User save(User user) {
        return userRepository.save(user);
    }

}
