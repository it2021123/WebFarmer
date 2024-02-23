package com.example.eagrotis.payload.response;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.sql.Date;
import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String name;
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private String role;

    public JwtResponse(String accessToken, Long id, String username, String name, Date birthDate, String role) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.name = name;
        this.birthDate = birthDate;
        this.role = role;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() { return role; }

    public Date getBirthDate() {return birthDate; }

    public void setBirthDate(Date birthDate) {this.birthDate = birthDate; }
}
