package com.example.apponlineshop.entity;

import com.example.apponlineshop.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AbsEntity {
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private Category category;
    @OneToOne
    private Maker maker;
    @Column(nullable = false)
    private String expireAmount;
    @Column(nullable = false)
    private String timeMode;
    @Column(nullable = false)
    private String percentProfit;

}
