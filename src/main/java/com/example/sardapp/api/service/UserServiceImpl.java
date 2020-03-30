package com.example.sardapp.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sardapp.entities.User;
import com.example.sardapp.api.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> findAll()
    {
        List<User> listUsers= userDAO.findAll();
        return listUsers;
    }

    @Override
    public User findByUsername(String username)
    {
        User user = userDAO.findByUsername(username);
        return user;
    }

    @Override
    public void save(User user)
    {
        userDAO.save(user);
    }

    @Override
    public void deleteByUsername(String username)
    {
        userDAO.deleteByUsername(username);
    }
}
