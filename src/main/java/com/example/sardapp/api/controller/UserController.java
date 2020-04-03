package com.example.sardapp.api.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import com.example.sardapp.entities.User;
import com.example.sardapp.api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api")
public class UserController
{
    @Autowired
    private UserService userService;

    /*  Method: GET
        Obtain some data from database
    */
    /*  Get all users*/
    @GetMapping("/users")
    public ResponseEntity findAll()
    {
        List<User> users = userService.findAll();
        if(users == null)
        {
            return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    /* Get one user specified by an email*/
    @GetMapping("/users/{email}")
    public ResponseEntity getUser(@PathVariable String email)
    {
        User user = userService.findByEmail(email);

        if(user == null)
        {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }


    /*  Method: POST
        Create new entry into database
    */
    /*  Create new user*/
    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody User user) throws InvalidKeySpecException, NoSuchAlgorithmException
    {
        User userFound = userService.findByEmail(user.getEmail());

        if(userFound != null)
        {
            return new ResponseEntity("User already exists with this email", HttpStatus.CONFLICT);
        }
        //signUp(user.getEmail(), user.getPassword());
        userService.save(user);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }


    /*  Method: PUT
        Modify an entry from database
    */
    /*  Modify a user's profile image specified by an email */
    @PutMapping("/users/{email}/profileImage")
    public ResponseEntity updateProfileImage(@PathVariable String email, @RequestBody MultipartFile image) throws IOException
    {
        User user = userService.findByEmail(email);
        if(user == null)
        {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }
        userService.addProfileImage(email, image);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }

    /*  Modify a user's password specified by an email */
    @PutMapping("/users/{email}")
    public ResponseEntity updatePassword(@PathVariable String email, @RequestBody String password) throws InvalidKeySpecException, NoSuchAlgorithmException
    {
        User user = userService.findByEmail(email);
        if(user == null)
        {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }
        user.setPassword(password);
        userService.save(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    /*  Method: DELETE
        Delete an entry from database
    */
    /*  Delete an specific user by its email */
    @DeleteMapping("users/{email}")
    public ResponseEntity deleteUser(@PathVariable String email)
    {
        User user = userService.findByEmail(email);
        if (user == null)
        {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }
        userService.deleteByEmail(email);
        return new ResponseEntity("User deleted successfully", HttpStatus.NO_CONTENT);
    }

}
