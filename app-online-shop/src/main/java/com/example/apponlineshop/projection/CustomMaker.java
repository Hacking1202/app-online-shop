package com.example.apponlineshop.projection;

import com.example.apponlineshop.entity.Maker;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "customMaker", types = Maker.class)
public interface CustomMaker {
    Integer getId();
    String getName();
}
