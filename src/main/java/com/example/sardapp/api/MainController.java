package com.example.sardapp.api;

import com.example.sardapp.api.service.*;
import com.example.sardapp.api.dao.*;
import com.example.sardapp.api.controller.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainController {
    public static void main(String[] args) {
        SpringApplication.run(MainController.class, args);
    }
}