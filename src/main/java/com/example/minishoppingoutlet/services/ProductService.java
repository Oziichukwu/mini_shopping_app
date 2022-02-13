package com.example.minishoppingoutlet.services;

import com.example.minishoppingoutlet.data.dtos.ProductDto;
import com.example.minishoppingoutlet.data.models.Product;
import com.example.minishoppingoutlet.exceptions.ProductException;

import java.util.List;

public interface ProductService {

    void addProductToCart(ProductDto productDto);

    void removeProduct(String productId) throws ProductException;

    void removeProduct(String productId, int quantity);

    Product findProduct(String productId)throws ProductException;

    ProductDto findProductById(String productId);

    List<ProductDto> getAllProduct();

    ProductDto updateProduct(String productId, ProductDto updateProducts);

    List<ProductDto> getAllProductsInCategory(String category);

    List<ProductDto> getAllProductContainingProductName(String productName);

    List<ProductDto> getAllProductContainingProductDescription(String description);
}
