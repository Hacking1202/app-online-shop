package com.example.apponlineshop.repository;

import com.example.apponlineshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsByNameIgnoreCase(String name);
}
