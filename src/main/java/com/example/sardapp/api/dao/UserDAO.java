package com.example.sardapp.api.dao;

import java.io.IOException;
import java.util.List;
import com.example.sardapp.entities.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserDAO
{
    public List<User> findAll();
    public User findByEmail(String email);
    public boolean save(User user);
    public boolean deleteByEmail(String email);
    public boolean addProfileImage(String email, MultipartFile image) throws IOException;

}
