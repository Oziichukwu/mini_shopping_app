package com.example.minishoppingoutlet.services;

import com.example.minishoppingoutlet.data.dtos.ProductDto;
import com.example.minishoppingoutlet.data.models.Product;
import com.example.minishoppingoutlet.data.repositories.ProductRepository;
import com.example.minishoppingoutlet.exceptions.ProductException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.web.servlet.oauth2.client.OAuth2ClientSecurityMarker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public void addProductToCart(ProductDto productDto) {

        Product product = modelMapper.map(productDto, Product.class);
        addNewProduct(product);
    }

    private void addNewProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void removeProduct(String productId) throws ProductException{

        Product productToRemove = findByProductId(productId);
        removeOneProduct(productToRemove);
    }

    private void removeOneProduct(Product productToRemove) {
        productRepository.delete(productToRemove);
    }

    private Product findByProductId(String productId)throws ProductException {
        return productRepository.findById(productId).orElseThrow(()->
                new ProductException("No user found with id" + productId));
    }

    @Override
    public void removeProduct(String productId, int quantity) {

    }

    @Override
    public Product findProduct(String productId) throws ProductException{
        return findByProductId(productId);
    }

    @Override
    public ProductDto findProductById(String productId) {

        Product product = findByProductId(productId);
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<ProductDto> products = new ArrayList<>();
        for (Product product : productRepository.findAll()){
            products.add(modelMapper.map(product, ProductDto.class));
        }
        return products;
    }

    @Override
    public ProductDto updateProduct(String productId, ProductDto updateProducts) {
        Product productToUpdate = findByProductId(productId);
        modelMapper.map(updateProducts, productToUpdate);
        Product updatedProduct = productRepository.save(productToUpdate);
        return modelMapper.map(updatedProduct, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProductsInCategory(String category) {
        List <ProductDto> products = new ArrayList<>();
        for(Product product : productRepository.findProductsByCategoryId(category)){
            products.add(modelMapper.map(product, ProductDto.class));
        }
        return products;
    }

    @Override
    public List<ProductDto> getAllProductContainingProductName(String productName) {
        List<ProductDto> products = new ArrayList<>();
        for(Product product : productRepository.findAllByName(productName)){
            products.add(modelMapper.map(product, ProductDto.class));
        }
        return products;
    }

    @Override
    public List<ProductDto> getAllProductContainingProductDescription(String description) {
        List<ProductDto> products = new ArrayList<>();

        for(Product product : productRepository.findProductsByDescriptionContaining(description)){
            products.add(modelMapper.map(product, ProductDto.class));
        }
        return products;
    }
}
