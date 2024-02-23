package com.example.eagrotis.payload.request;

import com.example.eagrotis.entity.Application;
import com.example.eagrotis.entity.Employee;
import com.example.eagrotis.entity.Farmer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class SumbitRequest {


    @NotBlank
    @Size(min = 5, max = 20)
    private String agriculturalCooperativeName;


    private Application.statusEnum status;

    @NotNull
    private List<String> members;

    private List<String> products;

    //@NotNull
    private List<String> cultivation_area;

    private String farmer;

    public String getAgriculturalCooperativeName() {
        return agriculturalCooperativeName;
    }

    public void setAgriculturalCooperativeName(String agriculturalCooperativeName) {
        this.agriculturalCooperativeName = agriculturalCooperativeName;
    }

    public Application.statusEnum getStatus() {
        return status= Application.statusEnum.UNSEEN;
    }

    public void setStatus(Application.statusEnum status) {
        this.status = status;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public List<String> getCultivation_area() {
        return cultivation_area;
    }

    public void setCultivation_area(List<String> cultivation_area) {
        this.cultivation_area = cultivation_area;
    }

    public String getFarmer() {
        return farmer;
    }

    public void setFarmer(String farmer) {
        this.farmer = farmer;
    }
}
