package com.example.sardapp.api.controller;

import java.util.List;

import com.example.sardapp.entities.User;
import com.example.sardapp.api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> findAll()
    {
        return userService.findAll();
    }

    @GetMapping("/users/{username}")
    public User getUser(@PathVariable String username)
    {
        User user = userService.findByUsername(username);

        if (user == null) throw new RuntimeException("User not found -" + username);

        return user;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user)
    {
        user.setUsername("userTest");
        userService.save(user);
        return user;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user)
    {
        userService.save(user);
        return user;
    }

    @DeleteMapping("users/{username}")
    public String deleteUser(@PathVariable String username)
    {
        User user = userService.findByUsername(username);
        if(user == null) throw new RuntimeException("User not found -" + username);
        userService.deleteByUsername(username);
        return "Deleted user id - " + username;
    }

}
