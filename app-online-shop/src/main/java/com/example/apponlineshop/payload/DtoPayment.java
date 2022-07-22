package com.example.apponlineshop.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoPayment {
    private UUID id;
    private UUID userId;
    private double paySum;
    private String payType;
}
