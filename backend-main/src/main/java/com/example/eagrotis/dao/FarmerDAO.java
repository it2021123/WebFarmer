package com.example.eagrotis.dao;

import com.example.eagrotis.entity.Admin;
import com.example.eagrotis.entity.Farmer;

import java.util.List;

public interface FarmerDAO {

    public List<Farmer> getFarmers();
    public Farmer getFarmer(Long farmer_id);
    public Farmer saveFarmer(Farmer farmer);
    public void deleteFarmer(Long farmer_id);

}
