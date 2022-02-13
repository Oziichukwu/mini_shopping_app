package com.example.minishoppingoutlet.data.repositories;

import com.example.minishoppingoutlet.data.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart ,String> {

}
