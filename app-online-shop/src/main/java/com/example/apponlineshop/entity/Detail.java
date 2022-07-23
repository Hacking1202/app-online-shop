package com.example.apponlineshop.entity;

import com.example.apponlineshop.entity.template.AbsEntity;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Detail extends AbsEntity {
    private String name;
    private String value;
}
