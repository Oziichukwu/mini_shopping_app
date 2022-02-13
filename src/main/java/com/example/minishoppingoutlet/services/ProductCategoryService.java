package com.example.minishoppingoutlet.services;

import com.example.minishoppingoutlet.data.dtos.ProductCategoryDto;
import com.example.minishoppingoutlet.data.models.ProductCategory;

import java.util.List;

public interface ProductCategoryService {


    void createCategory(ProductCategoryDto categoryDto);

    ProductCategoryDto getCategoryById(String categoryId);

    ProductCategoryDto updateCategory(String categoryId, ProductCategoryDto productCategoryDto);

    void deleteCategory(String categoryId);

    List<ProductCategoryDto> getAllCategory();

    ProductCategory getProductCategoryById(String id);
}
