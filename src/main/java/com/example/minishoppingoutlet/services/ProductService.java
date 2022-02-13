package com.example.minishoppingoutlet.services;

import com.example.minishoppingoutlet.data.dtos.ProductDto;
import com.example.minishoppingoutlet.data.models.Product;

import java.util.List;

public interface ProductService {

    void addProductToCart(ProductDto productDto);

    void removeProduct(String productId);

    void removeProduct(String productId, int quantity);

    Product findProduct(String productId);

    ProductDto findProductById(String productId);

    List<ProductDto> getAllProduct();

    ProductDto updateProduct(String productId, ProductDto updateProdcuts);

    List<ProductDto> getAllProductsInCategory(String category);

    List<ProductDto> getAllProductContainingProductName(String productName);

    List<ProductDto> getAllProductContainingProductDescription(String description);
}
