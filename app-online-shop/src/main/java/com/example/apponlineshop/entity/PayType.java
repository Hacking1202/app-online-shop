package com.example.apponlineshop.entity;

import com.example.apponlineshop.entity.template.AbsNameEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class PayType extends AbsNameEntity {
}
