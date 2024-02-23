package com.example.eagrotis.entity;

import com.example.eagrotis.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "name")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 120)
    private String password;


    @Temporal(TemporalType.DATE)
    private Date birthDate;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role role ;


    public User() {
    }

    public User(String username, String name, String password, Date birthDate) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {this.Id = id;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    public UserDTO toDTO() {
        UserDTO dto = new UserDTO();
        dto.setId(Id);
        dto.setUsername(username);
        dto.setPassword(password);
        dto.setName(name);
        dto.setBirthDate(birthDate);
        dto.setRoleName(role != null ? role.getName() : null);
        return dto;
    }




    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", Username='" + username + '\'' +
                ", Name='" + name + '\'' +
                ", Birthdate='" + birthDate + '\'' +
                '}';
    }
}