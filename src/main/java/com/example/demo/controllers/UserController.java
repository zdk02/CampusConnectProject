package com.example.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired 
	private UserService userService;
	
	private ResponseEntity<?> checkAdmin(Long requesterId) {
	    User requester = userService.getUserById(requesterId);
	    if (requester == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            .body("User not found");
	    }
	    if (requester.getRole() != Role.ADMIN) {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN)
	            .body("Only admins can perform this action");
	    }
	    return null; 
	}
	
	//update user
	@PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @RequestBody User updatedUser,
            @RequestParam Long requesterId) {

        ResponseEntity<?> authCheck = checkAdmin(requesterId);
        if (authCheck != null) return authCheck;

        return ResponseEntity.ok(userService.updateUser(id, updatedUser));
    }

	//delete user
	@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(
            @PathVariable Long id,
            @RequestParam Long requesterId) {

        ResponseEntity<?> authCheck = checkAdmin(requesterId);
        if (authCheck != null) return authCheck;

        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
	
	//Get All Users
	@GetMapping
    public ResponseEntity<?> getAllUsers(@RequestParam Long requesterId) {
        ResponseEntity<?> authCheck = checkAdmin(requesterId);
        if (authCheck != null) return authCheck;

        return ResponseEntity.ok(userService.getAllUsers());
    }

    //Get User by ID
	@GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User not found");
        }
        return ResponseEntity.ok(user);
    }
    
    //Find user by username
	@GetMapping("/by-username")
    public ResponseEntity<?> findByUsername(@RequestParam String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User not found");
        }
        return ResponseEntity.ok(user);
    }
    
    //Find user by role
	@GetMapping("/by-role")
    public ResponseEntity<?> getUsersByRole(@RequestParam Role role) {
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }
    
    //Get Active Students Count (Admin)
	@GetMapping("/active-students/count")
    public ResponseEntity<?> getActiveStudentsCount(@RequestParam Long requesterId) {
        ResponseEntity<?> authCheck = checkAdmin(requesterId);
        if (authCheck != null) return authCheck;

        return ResponseEntity.ok(userService.getActiveStudentsCount());
    }

    //Deactivate a user (Admin)
	@PutMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivateUser(
            @PathVariable Long id,
            @RequestParam Long requesterId) {

        ResponseEntity<?> authCheck = checkAdmin(requesterId);
        if (authCheck != null) return authCheck;

        userService.deactivateUser(id);
        return ResponseEntity.ok("User deactivated successfully");
    }
}