package com.example.sardapp.api.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.example.sardapp.entities.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService
{
    public List<User> findAll();
    public User findByEmail(String email);
    public void save(User user);
    public void deleteByEmail(String email);
    public void addProfileImage(String email, MultipartFile image) throws IOException;
}
