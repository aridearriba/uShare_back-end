package com.example.sardapp.controller;

import com.example.sardapp.entities.User;
import com.example.sardapp.entities.request.AddUserRequest;
import com.example.sardapp.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController
{
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @RequestMapping (method = RequestMethod.GET)
    public List<User> findAllUsers ()
    {
        return userRepository.findAll();
    }

    @RequestMapping (method = RequestMethod.POST)
    public void addUser(@RequestBody AddUserRequest addUserRequest)
    {
        User user = new User();
        user.setUsername(addUserRequest.getUsername());
        user.setPassword(addUserRequest.getPassword());
        user.setEmail(addUserRequest.getEmail());
        userRepository.save(user);
    }
}
