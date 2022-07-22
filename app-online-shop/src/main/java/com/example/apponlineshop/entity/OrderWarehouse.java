package com.example.apponlineshop.entity;

import com.example.apponlineshop.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor

public class OrderWarehouse extends AbsEntity {
    @OneToMany(fetch = FetchType.LAZY)
    private List<Order> order;

    @OneToMany(fetch = FetchType.LAZY)
    private List<WareHouse> wareHouse;

    private Double price;

    private Integer amount;

    private Integer delivered_amount;


}
