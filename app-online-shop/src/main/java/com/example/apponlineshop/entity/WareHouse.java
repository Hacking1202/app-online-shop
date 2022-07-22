package com.example.apponlineshop.entity;

import com.example.apponlineshop.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WareHouse
        extends AbsEntity {
    @OneToMany(mappedBy = "name", fetch = FetchType.LAZY)
    private List<Product> product;

    private Integer amount;

    private Date arrived_date;

    private Date made_at;

    private Date expire_date;

    private Double price;

    private Double sell_price;

    private Integer leftover;
}
