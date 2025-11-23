package com.example.demo.services;
import java.util.List;
import com.example.demo.models.Role;
import com.example.demo.models.User;

public interface UserService {

	//Admin can view all users
	public List<User> getAllUsers();
	
	//Admin can filter users by role
    public List<User> getUsersByRole(Role role);
    
    //Admin can view a user by ID
  	public User getUserById(Long id);
  	
    //Admin can find a user by username
    public User findByUsername(String username);
    
    //Admin can get the number of active students
    public int getActiveStudentsCount();
    
    // Admin can update a user
    public User updateUser(Long id, User updatedUser);

    // Admin can delete a user
    public void deleteUser(Long id);
    
    //Admin can deactivate a user account
    public void deactivateUser(Long id);
	
    //All users can log in (Student, Manager, Admin)
	public User login(String username, String password);
 
}