package com.example.apponlineshop.service;

import com.example.apponlineshop.entity.Category;
import com.example.apponlineshop.payload.ApiResponse;
import com.example.apponlineshop.payload.ReqCategory;
import com.example.apponlineshop.payload.ResCategory;
import com.example.apponlineshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public ApiResponse saveCategory(ReqCategory reqCategory) {
        Category category = new Category();
        category.setName(reqCategory.getName());
        if (reqCategory.getCategoryId()==null) {
            category.setCategory(null);
        } else {
            category.setCategory(categoryRepository.findById(reqCategory.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("getCategory")));
        }
        categoryRepository.save(category);
        return new ApiResponse("Saved Category", true);
    }
    public ResCategory getCategory(Category category) {
        return new ResCategory(
                category.getId(),
                category.getName(),
                category.getCategory()
        );
    }

    public List<ResCategory> getListCategory() {
        return categoryRepository.findAll().stream().map(this::getCategory).collect(Collectors.toList());
    }

    public ApiResponse editCategory(UUID id, ReqCategory reqCategory) {
        try {
            Optional<Category> optionalCategory = categoryRepository.findById(id);

            if (optionalCategory.isPresent()) {
                Category category = optionalCategory.get();
                category.setName(reqCategory.getName());
                if (reqCategory.getCategoryId() == null) {
                    category.setCategory(null);
                } else {
                    category.setCategory(categoryRepository.findById(reqCategory.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("getCategory")));
                }
                category.setName(reqCategory.getName());
                categoryRepository.save(category);
                return new ApiResponse("Edited client", true);

            }
            return new ApiResponse("Such Not Found", false);


        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }

    public ApiResponse deleteCategory(UUID id) {
            categoryRepository.deleteById(id);
            return new ApiResponse("Successfully deleted category", true);
    }
}