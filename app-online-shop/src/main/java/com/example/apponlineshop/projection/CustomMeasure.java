package com.example.apponlineshop.projection;

import com.example.apponlineshop.entity.Maker;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "customMeasure", types = Maker.class)
public interface CustomMeasure {
    Integer getId();
    String getName();
}
