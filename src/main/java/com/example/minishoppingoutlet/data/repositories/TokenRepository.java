package com.example.minishoppingoutlet.data.repositories;

import com.example.minishoppingoutlet.data.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, String> {

    Optional<Token> findByToken(String token);
}
