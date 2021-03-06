package com.example.minishoppingoutlet.services;

import com.example.minishoppingoutlet.data.dtos.ProductCategoryDto;
import com.example.minishoppingoutlet.data.models.ProductCategory;
import com.example.minishoppingoutlet.data.repositories.ProductCategoryRepository;
import com.example.minishoppingoutlet.exceptions.ProductCategoryException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public void createCategory(ProductCategoryDto categoryDto) {
        ProductCategory category = modelMapper.map(categoryDto, ProductCategory.class);
        saveCategory(category);
    }

    private void saveCategory(ProductCategory category) {

        productCategoryRepository.save(category);
    }

    @Override
    public ProductCategoryDto getCategoryById(String categoryId) {
        ProductCategory category = findById(categoryId);
        return modelMapper.map(category, ProductCategoryDto.class);
    }

    private ProductCategory findById(String categoryId) {
        return productCategoryRepository.findById(categoryId).orElseThrow(()->
                new ProductCategoryException("No category found with that Id" + categoryId));
    }

    @Override
    public ProductCategoryDto updateCategory(String categoryId, ProductCategoryDto productCategoryDto) {
        ProductCategory category = findById(categoryId);
        modelMapper.map(productCategoryDto, ProductCategory.class);
        productCategoryRepository.save(category);

        return modelMapper.map(category, ProductCategoryDto.class);
    }

    @Override
    public void deleteCategory(String categoryId) {
        deleteProductCategory(categoryId);
    }

    private void deleteProductCategory(String categoryId) {
        productCategoryRepository.deleteById(categoryId);
    }

    @Override
    public List<ProductCategoryDto> getAllCategory() {
        List<ProductCategoryDto> categories = new ArrayList<>();
        for (ProductCategory category : getAllProductCategories()){
            categories.add(modelMapper.map(category, ProductCategoryDto.class));
        }
        return categories;
    }

    private List<ProductCategory> getAllProductCategories() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory getProductCategoryById(String id) {
        return findById(id);
    }
}
