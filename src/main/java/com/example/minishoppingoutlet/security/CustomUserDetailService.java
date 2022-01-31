package com.example.minishoppingoutlet.security;

import com.example.minishoppingoutlet.data.models.User;
import com.example.minishoppingoutlet.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import static java.lang.String.format;


@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findUserByEmail(email).orElseThrow(()->
                new UsernameNotFoundException(format("user with email does not exist %s", email)));
        return UserPrincipal.create(user);
    }
}
