package com.example.minishoppingoutlet.services;

import com.example.minishoppingoutlet.data.dtos.ProductDto;
import com.example.minishoppingoutlet.data.models.Product;
import com.example.minishoppingoutlet.data.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public void addProductToCart(ProductDto productDto) {

    }

    @Override
    public void removeProduct(String productId) {

    }

    @Override
    public void removeProduct(String productId, int quantity) {

    }

    @Override
    public Product findProduct(String productId) {
        return null;
    }

    @Override
    public ProductDto findProductById(String productId) {
        return null;
    }

    @Override
    public List<ProductDto> getAllProduct() {
        return null;
    }

    @Override
    public ProductDto updateProduct(String productId, ProductDto updateProdcuts) {
        return null;
    }

    @Override
    public List<ProductDto> getAllProductsInCategory(String category) {
        return null;
    }

    @Override
    public List<ProductDto> getAllProductContainingProductName(String productName) {
        return null;
    }

    @Override
    public List<ProductDto> getAllProductContainingProductDescription(String description) {
        return null;
    }
}
