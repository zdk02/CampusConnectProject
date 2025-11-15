package com.example.demo.controllers;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.LoginDTO;
import com.example.demo.models.User;
import com.example.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.*;


@RequestMapping("/auth")
@RestController
public class AuthenticationController {

	@Autowired
    private UserService userService;
	
	@PostMapping("/login")
    public User login(@RequestBody LoginDTO loginrequest) {
        return userService.login(loginrequest.getUsername(), loginrequest.getPassword());
    }
	
}