package com.example.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired 
	private UserService userService;
	
	//Create User
	@PostMapping("/create")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	//Get All Users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    //Get User by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    
    //Find user by username
    @GetMapping("/by-username")
    public User findByUsername(@RequestParam String username) {
        return userService.findByUsername(username);
    }
    
    //Find user by role
    @GetMapping("/by-role")
    public List<User> getUsersByRole(@RequestParam Role role) {
        return userService.getUsersByRole(role);
    }
    
    //Get Active Students Count (Admin)
    @GetMapping("/active-students/count")
    public int getActiveStudentsCount() {
        return userService.getActiveStudentsCount();
    }
    
    //Deactivate a user (Admin)
    @PutMapping("/{id}/deactivate")
    public String deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);
        return "User deactivated successfully";
    }
}

//Move admin-only endpoints under /admin/users/...