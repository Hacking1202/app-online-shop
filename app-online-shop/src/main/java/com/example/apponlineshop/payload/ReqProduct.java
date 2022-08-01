package com.example.apponlineshop.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReqProduct {
    private String name;
    private UUID category_id;
    private Integer maker_id;
    private String expireAmount;
    private String timeMode;
    private Double percentProfit;
    private Integer measure_id;
    private String percentProfit;
    private Integer detail_id;

}

