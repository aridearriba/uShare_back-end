package com.example.sardapp.database;

import com.example.sardapp.api.dao.UserDAO;
import com.example.sardapp.api.dao.UserDAOImpl;
import com.example.sardapp.entities.User;
import com.ja.security.PasswordHash;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class UserManage
{
    private UserManage(){
        throw new IllegalStateException("Utility class");
    }

    public static void signUp(String email, String password) throws InvalidKeySpecException, NoSuchAlgorithmException
    {
        String hashedPassword = new PasswordHash().createHash(password);
        // create user
        User user = new User(email, hashedPassword);
        // save user
        UserDAO users = new UserDAOImpl();
        users.save(user);
    }

    public static boolean login(String email, String password) throws InvalidKeySpecException, NoSuchAlgorithmException
    {
        User user = new UserDAOImpl().findByEmail(email);
        try {
            return new PasswordHash().validatePassword(password, user.getPassword());
        }
        catch (NullPointerException e){
            return false;
        }
    }

    public static void delete(String email)
    {
        UserDAO users = new UserDAOImpl();
        users.deleteByEmail(email);
    }

    public static void addProfileImage(String email, byte[] image)
    {
        User user = new UserDAOImpl().findByEmail(email);
        user.setImage(image);
        UserDAO users = new UserDAOImpl();
        users.save(user);
    }
}

