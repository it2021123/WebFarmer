package com.example.eagrotis.entity;

import jakarta.persistence.Entity;

import java.sql.Date;
import java.util.List;

@Entity
public class Admin extends User{


    public Admin(String username, String name, String password, Date birthDate){
        super(username,name,password,birthDate);
    }

    public Admin() {

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