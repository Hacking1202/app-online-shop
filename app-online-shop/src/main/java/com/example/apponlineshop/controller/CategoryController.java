package com.example.apponlineshop.controller;

import com.example.apponlineshop.entity.Category;
import com.example.apponlineshop.payload.ApiResponse;
import com.example.apponlineshop.payload.ReqCategory;
import com.example.apponlineshop.payload.ResCategory;
import com.example.apponlineshop.repository.CategoryRepository;
import com.example.apponlineshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping
    @ResponseBody
    public HttpEntity<?> saveCategory(@RequestBody ReqCategory reqCategory) {
        ApiResponse apiResponse = categoryService.saveCategory(reqCategory);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResCategory getOneCategory(Category category){
        return categoryService.getCategory(category);
    }

    @GetMapping("/list")
    @ResponseBody
    public HttpEntity<?> getList(){
        List<Category> all = categoryRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public HttpEntity<?> editCategory(@PathVariable UUID id, @RequestBody ReqCategory reqCategory) {
        ApiResponse apiResponse = categoryService.editCategory(id, reqCategory);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public HttpEntity<?> deleteCategory(@PathVariable UUID id){
        ApiResponse apiResponse = categoryService.deleteCategory(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
