package com.example.apponlineshop.repository;

import com.example.apponlineshop.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;

@CrossOrigin
@RepositoryRestResource(path = "detail", collectionResourceRel = "list", excerptProjection = CustomDetail.class)
public interface DetailRepository extends JpaRepository<Detail, UUID> {
}
