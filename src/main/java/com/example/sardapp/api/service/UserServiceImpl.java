package com.example.sardapp.api.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sardapp.entities.User;
import com.example.sardapp.api.dao.UserDAO;
import org.springframework.web.multipart.MultipartFile;

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
    public User findByEmail(String email)
    {
        User user = userDAO.findByEmail(email);
        return user;
    }

    @Override
    public void save(User user)
    {
        userDAO.save(user);
    }

    @Override
    public void deleteByEmail(String email)
    {
        userDAO.deleteByEmail(email);
    }

    @Override
    public void addProfileImage(String email, MultipartFile image) throws IOException
    {
        userDAO.addProfileImage(email, image);
    }
}
