package com.example.eagrotis.repository;

import com.example.eagrotis.entity.Farmer;
import com.example.eagrotis.entity.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RepositoryRestResource(path = "farmer")
@Hidden
public interface FarmerRepository extends JpaRepository<Farmer, Long> {

    //Optional<User> findByUsername(String username);
    Farmer findByUsername(String username);
    Boolean existsByUsername(String username);

    Boolean existsByName(String name);

}
