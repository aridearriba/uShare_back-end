package com.example.sardapp.database;

import com.example.sardapp.api.dao.UserDAO;
import com.example.sardapp.api.dao.UserDAOImpl;
import com.example.sardapp.entities.User;
import com.ja.security.PasswordHash;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class UserManage
{
    private UserManage(){
        throw new IllegalStateException("Utility class");
    }

    public static void signUp(String username, String password, String email) throws InvalidKeySpecException, NoSuchAlgorithmException
    {
        String hashedPassword = new PasswordHash().createHash(password);
        // create user
        User user = new User(username, hashedPassword, email);
        // save user
        UserDAO users = new UserDAOImpl();
        users.save(user);
    }

    public static boolean login(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException
    {
        User user = new UserDAOImpl().findByUsername(username); // ID = EMAIL
        try {
            return new PasswordHash().validatePassword(password, user.getPassword());
        }
        catch (NullPointerException e){
            return false;
        }
    }

    public static void delete(String username)
    {
        UserDAO users = new UserDAOImpl();
        users.deleteByUsername(username);
    }
}

