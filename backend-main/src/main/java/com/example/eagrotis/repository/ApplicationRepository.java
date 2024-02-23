package com.example.eagrotis.repository;

import com.example.eagrotis.entity.Application;
import com.example.eagrotis.entity.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "application")
public interface ApplicationRepository extends JpaRepository<Application, Long>  {

    Optional<Application> findApplicationByAgriculturalCooperativeName(String name);
    Boolean existsByAgriculturalCooperativeName(String name);

    Application getApplicationById(Long applicationId);

    List<Application> findByFarmer(Farmer farmer);

}
