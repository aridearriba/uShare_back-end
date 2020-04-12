package com.example.sardapp.api.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import com.ja.security.PasswordHash;
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
    public void save(User user) throws InvalidKeySpecException, NoSuchAlgorithmException
    {
        // Convert password to hash and set it
        String hashedPassword = new PasswordHash().createHash(user.getPassword());
        user.setPassword(hashedPassword);

        // Save user to DB
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
