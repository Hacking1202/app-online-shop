package com.example.apponlineshop.entity;

import com.example.apponlineshop.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@CrossOrigin
public class Category extends AbsEntity {
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private Category category;
}
