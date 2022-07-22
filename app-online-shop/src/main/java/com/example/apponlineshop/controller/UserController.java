package com.example.apponlineshop.controller;

import com.example.apponlineshop.entity.Category;
import com.example.apponlineshop.payload.ApiResponse;
import com.example.apponlineshop.payload.DtoUser;
import com.example.apponlineshop.repository.CategoryRepository;
import com.example.apponlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CategoryRepository categoryRepository;
    @PostMapping
    @ResponseBody
    public HttpEntity<?> saveUser(@RequestBody DtoUser dtoUser) {
        ApiResponse apiResponse = userService.saveUser(dtoUser);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }
    @PutMapping("/{id}")
    @ResponseBody
    public HttpEntity<?> editUser(@PathVariable UUID id, @RequestBody DtoUser dtoUser) {
        ApiResponse apiResponse = userService.editUser(id, dtoUser);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }
    @GetMapping("/list")
    @ResponseBody
    public HttpEntity<?> getList(){
        List<Category> all = categoryRepository.findAll();
        return ResponseEntity.ok(all);
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public ApiResponse deleteUser(@PathVariable UUID id){
        return userService.deleteUser(id);
    }

}
