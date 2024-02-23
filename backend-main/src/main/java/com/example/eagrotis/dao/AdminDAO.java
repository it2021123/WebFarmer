package com.example.eagrotis.dao;

import com.example.eagrotis.entity.Admin;

import java.util.List;

public interface AdminDAO {

    public List<Admin> getAdmins();
    public Admin getAdmin(Long admin_id);
    public Admin saveAdmin(Admin admin);
    public void deleteAdmin(Long admin_id);

}
