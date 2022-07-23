package com.example.apponlineshop.repository;

import com.example.apponlineshop.entity.Category;
import com.example.apponlineshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.UUID;
@CrossOrigin
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findByNameEquals(String name);
}
