package com.example.eagrotis.dao;

import com.example.eagrotis.dto.UserDTO;
import com.example.eagrotis.entity.User;

import java.util.List;

public interface UserDAO {

    public List<Object[]> getUsers();
    public User getUser(Long user_id);
    public User saveUser(User user);
    public void deleteUser(Long user_id);
    public UserDTO getUserDTO(Long userId);


}
