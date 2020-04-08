package com.example.sardapp.api.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import com.example.sardapp.entities.User;
import com.example.sardapp.api.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@Api(tags = "User")
@RequestMapping("/api/users")
public class UserController
{
    @Autowired
    private UserService userService;

    /*  Method: GET
        Obtain some data from database
    */
    /*  Get all users*/
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all users", notes = "Get all users with their information")
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
    @GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get one user", notes = "Get all user information by its email")
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
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create new user", notes = "Create new user providing its information")
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
    @PutMapping(value = "/{email}/updateProfileImage", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modify user's profile image", notes = "Modify user's profile image by its email")
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
    @PutMapping(value = "/{email}/updatePassword", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modify user's password", notes = "Modify user's password by its email")
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
    @DeleteMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a user", notes = "Delete a user by its email")
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
