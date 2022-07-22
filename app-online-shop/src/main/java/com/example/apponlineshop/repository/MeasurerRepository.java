package com.example.apponlineshop.repository;

import com.example.apponlineshop.entity.Measure;
import com.example.apponlineshop.projection.CustomMaker;
import com.example.apponlineshop.projection.CustomMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "measure", collectionResourceRel = "list", excerptProjection = CustomMeasure.class)
public interface MeasurerRepository extends JpaRepository<Measure, Integer> {
}
