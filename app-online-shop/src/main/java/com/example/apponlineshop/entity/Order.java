package com.example.apponlineshop.entity;

import com.example.apponlineshop.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Order extends AbsEntity {
    @ManyToOne
    private Payment payment;

    @Column(nullable = false)
    public Date shoppingDate;

    @Column(nullable = false)
    public boolean delivered;
}
