package com.example.minishoppingoutlet.data.repositories;

import com.example.minishoppingoutlet.data.models.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,String> {

}
