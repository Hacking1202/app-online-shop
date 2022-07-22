package com.example.apponlineshop.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResOrder {
    public UUID id;
    private UUID paymentId;
    private Date shoppingDate;
    private boolean delivered;
}
