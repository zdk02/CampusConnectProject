package com.example.demo.controllers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

	@Autowired
    private UserService userService;
	
	@PostMapping("/login")
    public ResponseEntity<User> login(
            @RequestParam String username, 
            @RequestParam String password) {
        
        User user = userService.login(username, password);
        
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        
        return ResponseEntity.ok(user);
    }
}