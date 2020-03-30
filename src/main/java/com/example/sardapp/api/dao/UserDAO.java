package com.example.sardapp.api.dao;


import java.util.List;
import com.example.sardapp.entities.User;

public interface UserDAO
{
    public List<User> findAll();
    public User findByUsername(String username);
    public boolean save(User user);
    public boolean deleteByUsername(String username);
}
