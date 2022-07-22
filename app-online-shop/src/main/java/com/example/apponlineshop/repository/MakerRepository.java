package com.example.apponlineshop.repository;

import com.example.apponlineshop.entity.Maker;
import com.example.apponlineshop.projection.CustomMaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "maker", collectionResourceRel = "list", excerptProjection = CustomMaker.class)
public interface MakerRepository extends JpaRepository<Maker, Integer> {
}
