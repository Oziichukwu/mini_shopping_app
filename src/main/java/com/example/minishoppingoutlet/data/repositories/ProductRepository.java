package com.example.minishoppingoutlet.data.repositories;

import com.example.minishoppingoutlet.data.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findProductsByCategoryId(String productId);

    List<Product>findProductsByDescriptionContaining(String description);

    List<Product> findAllByName(String productName);

    List<Product> findProductByPriceGreaterThan(BigDecimal price);
}
