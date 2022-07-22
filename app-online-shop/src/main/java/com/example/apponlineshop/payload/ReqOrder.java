package com.example.apponlineshop.payload;

import lombok.*;

import java.util.Date;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqOrder {
    private UUID paymentId;
    private Date shoppingDate;
    private boolean delivered;
}
