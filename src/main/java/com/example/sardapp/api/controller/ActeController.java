package com.example.sardapp.api.controller;


import com.example.sardapp.api.service.ActeService;
import com.example.sardapp.entities.Acte;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@Api(tags = "Acte")
@RequestMapping("/api/actes")
public class ActeController {

    @Autowired
    private ActeService acteService;

    /*  Method: GET
        Obtain some data from database
    */
    /*  Get all actes*/
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all actes", notes = "Get all actes with their respective information")
    public ResponseEntity findAll()
    {
        List<Acte> actes = acteService.findAll();
        if(actes == null)
        {
            return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<List<Acte>>(actes, HttpStatus.OK);
    }

    /* Get one user specified by an email*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get one acte", notes = "Get all information of the acte specified by id")
    public ResponseEntity getActe(@PathVariable Integer id)
    {
        Acte acte = acteService.findById(id);

        if(acte == null)
        {
            return new ResponseEntity("Acte not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(acte, HttpStatus.OK);
    }

    /*  Method: POST
        Create new entry into database
    */
    /*  Create new user*/
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create new acte", notes = "Create new acte providing its information")
    public ResponseEntity addActe(@RequestBody Acte acte) throws InvalidKeySpecException, NoSuchAlgorithmException
    {
        Acte acteFound = acteService.findById(acte.getId());

        if(acteFound != null)
        {
            return new ResponseEntity("Acte already exists with this Id", HttpStatus.CONFLICT);
        }
        //signUp(user.getEmail(), user.getPassword());
        acteService.save(acte);
        return new ResponseEntity(acte, HttpStatus.CREATED);
    }

    /*  Method: PUT
        Modify an entry from database
    */

    /*  Method: DELETE
        Delete an entry from database
    */
    /*  Delete an specific acte by its id */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete an acte", notes = "Delete an acte by its id")
    public ResponseEntity deleteActe(@PathVariable Integer id)
    {
        Acte acte = acteService.findById(id);
        if (acte == null)
        {
            return new ResponseEntity("Acte not found", HttpStatus.NOT_FOUND);
        }
        acteService.deleteById(id);
        return new ResponseEntity("Acte deleted successfully", HttpStatus.NO_CONTENT);
    }
}
