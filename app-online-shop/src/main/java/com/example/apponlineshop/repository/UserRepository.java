package com.example.apponlineshop.repository;

import com.example.apponlineshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByChatIdEquals(Long chatId);
}
