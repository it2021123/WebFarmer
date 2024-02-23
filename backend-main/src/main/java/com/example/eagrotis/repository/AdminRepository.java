package com.example.eagrotis.repository;

import com.example.eagrotis.entity.Admin;
import com.example.eagrotis.entity.User;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(path = "admin")
@Hidden
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);

    Boolean existsByName(String name);

}
