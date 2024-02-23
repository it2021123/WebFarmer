package com.example.eagrotis.payload.request;

import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Date;

public class UpdateRequest {

    private  Long Id;

    //@NotBlank
    @Size(min = 3, max = 20)
    private String username;

    //@NotBlank
    @Size(max = 50)
    private String name;

    private String role;

    //@NotNull
    @Temporal(TemporalType.DATE)
    private Date birthDate;


    @Size(min = 6, max = 40)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Long getId() { return Id; }

    public void setId(Long id) {
        Id = id;
    }
}

