package com.example.apponlineshop.entity;

import com.example.apponlineshop.entity.template.AbsNameEntity;
import lombok.*;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter


public class Maker extends AbsNameEntity {
}
