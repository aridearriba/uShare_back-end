package com.example.sardapp.api.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import com.example.sardapp.entities.User;
import com.example.sardapp.api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.sardapp.database.UserManage.signUp;

@RestController
@RequestMapping("/api")
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity findAll()
    {
        List<User> users = userService.findAll();
        if(users == null)
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity getUser(@PathVariable String username)
    {
        User user = userService.findByUsername(username);

        if(user == null)
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody User user) throws InvalidKeySpecException, NoSuchAlgorithmException
    {
        if(user == null)
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        User userExists = userService.findByUsername(user.getUsername());

        if(userExists != null)
        {
            return new ResponseEntity("Username used", HttpStatus.BAD_REQUEST);
        }
        signUp(user.getUsername(), user.getPassword(), user.getEmail());
        return new ResponseEntity("User created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/users/{username}")
    public ResponseEntity updateUser(@RequestBody User user)
    {
        if(user == null)
        {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        userService.save(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @DeleteMapping("users/{username}")
    public ResponseEntity deleteUser(@PathVariable String username)
    {
        User user = userService.findByUsername(username);
        if(user == null) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        userService.deleteByUsername(username);
        return new ResponseEntity("User deleted successfully", HttpStatus.NO_CONTENT);
    }

}
