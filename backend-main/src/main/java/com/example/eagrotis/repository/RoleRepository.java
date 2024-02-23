package com.example.eagrotis.repository;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.eagrotis.entity.Role;
import java.util.Optional;

@Repository
@Hidden
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String roleName);
}