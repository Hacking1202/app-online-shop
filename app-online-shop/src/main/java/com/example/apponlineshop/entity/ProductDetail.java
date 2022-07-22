package com.example.apponlineshop.entity;

import com.example.apponlineshop.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ProductDetail extends AbsEntity {
    @OneToOne
    private Product product;
    @OneToOne
    private Detail detail;

}
