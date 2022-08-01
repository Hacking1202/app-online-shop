package com.example.apponlineshop.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class  DtoUser {
    private UUID id;
    private Long chatId;
    private String username;
    private String phoneNumber;
    private String password;

    public DtoUser(Long chatId, String username, String phoneNumber, String password) {
        this.chatId = chatId;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
