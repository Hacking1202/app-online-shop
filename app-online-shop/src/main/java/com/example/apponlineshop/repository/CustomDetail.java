package com.example.apponlineshop.repository;

import com.example.apponlineshop.entity.Detail;
import org.springframework.data.rest.core.config.Projection;

import java.util.UUID;


@Projection(name = "customDetail", types = Detail.class)
public interface CustomDetail {
    UUID getId();
    String getName();
    String getValue();
}
