package com.example.sardapp.api.service;

import java.util.List;
import com.example.sardapp.entities.User;

public interface UserService
{
    public List<User> findAll();
    public User findByUsername(String username);
    public void save(User user);
    public void deleteByUsername(String username);
}
