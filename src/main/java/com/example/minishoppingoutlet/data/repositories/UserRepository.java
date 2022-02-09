package com.example.minishoppingoutlet.data.repositories;

import com.example.minishoppingoutlet.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

     Optional<User> findUserByEmail(String email);

     boolean existsByEmail (String email);

}
