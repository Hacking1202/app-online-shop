package com.example.apponlineshop.payload;

import com.example.apponlineshop.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ResCategory {
    private UUID id;
    private String name;
    private Category categoryId;
}
