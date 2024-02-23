package com.example.eagrotis.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.sql.Date;
import java.util.List;

@Entity
public class Farmer extends User{
    public Farmer() {
    }

    @OneToMany(mappedBy = "farmer", cascade ={ CascadeType.REMOVE})
    private List<Application> applications;

    public Farmer(String username, String name, String password, Date birthDate) {
        super(username, name, password, birthDate);
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + getId() +
                ", Username='" + getUsername() + '\'' +
                ", Name='" + getName() + '\'' +
                ", Birthdate='" + getBirthDate() + '\'' +
                '}';
    }
}
