package com.example.apponlineshop.service;

import com.example.apponlineshop.entity.User;
import com.example.apponlineshop.payload.ApiResponse;
import com.example.apponlineshop.payload.DtoUser;
import com.example.apponlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void addOrEdit(DtoUser dtoUser, User user) {
        user.setChatId(dtoUser.getChatId());
        user.setUsername(dtoUser.getUsername());
        user.setPassword(dtoUser.getPassword());
        user.setPhoneNumber(dtoUser.getPhoneNumber());
        userRepository.save(user);
    }

    public ApiResponse saveUser(DtoUser dtoUser) {
        User user = new User();
        addOrEdit(dtoUser, user);
        return new ApiResponse("Saved User", true);
    }

    public ApiResponse editUser(UUID id, DtoUser dtoUser) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            User user = byId.get();
            addOrEdit(dtoUser, user);
            return new ApiResponse("Edited User", true);

        }
        return new ApiResponse("Error", false);
    }

    public ApiResponse deleteUser(UUID id) {
        userRepository.deleteById(id);
        return new ApiResponse("Deleted User", true);
    }

    public boolean isRegister(Long chatId) {
        try {
            return userRepository.existsByChatIdEquals(chatId);
        }catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginUsername(String username) {
        try {
            return userRepository.existsByUsernameEquals(username);
        }catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginPassword(String password) {
        try {
            return userRepository.existsByPasswordEquals(password);
        }catch (Exception e) {
            return false;
        }
    }
}
