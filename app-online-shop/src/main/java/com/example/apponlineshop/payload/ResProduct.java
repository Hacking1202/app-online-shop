package com.example.apponlineshop.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ResProduct {
    private UUID id;
    private String name;
    private String category;
    private String maker;
    private String measure;
    private String expireAmount;
    private String timeMode;
    private String percentProfit;
}
