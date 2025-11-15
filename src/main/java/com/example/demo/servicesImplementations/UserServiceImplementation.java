package com.example.demo.servicesImplementations;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.services.UserService;

@Service 
public class UserServiceImplementation implements UserService{
	
	private final List<User> users = new ArrayList<>();
	
	public UserServiceImplementation() {
	       users.add(new User(1L, "john", "john123", "John Haddad", "Admin", true));
	       users.add(new User(2L, "jane", "jane1", "Jane Zein", "Club Manager", true));
	       users.add(new User(3L, "fatima", "fatima3", "Fatima Jaffal", "Student", true));
	}

	@Override
	public User createUser(User user) {
		for (User u : users) {
		    if (u.getUsername().equalsIgnoreCase(user.getUsername())) {
		        throw new RuntimeException("Username already exists");
		    }
		}
		users.add(user);
		return user;
	}
	
	@Override
	public List<User> getAllUsers() {
		return new ArrayList<>(users);
	}

	@Override
	public User getUserById(Long id) {
		for (User user : users) {
			if (user.getId().equals(id)) {
	            return user;
	        }
		}
		throw new NoSuchElementException("User not found");
	}
	
	@Override
	public User findByUsername(String username) {
		for (User user : users) {
			if (user.getUsername().equalsIgnoreCase(username)) {
	            return user;
	        }
		}
		throw new NoSuchElementException("User not found");
	}
	
	@Override
	public User login(String username, String password) {
	   User user = findByUsername(username);

	   if (!user.getPassword().equals(password)) {
			throw new RuntimeException("Incorrect password");
	   }
	   return user;  
	}
	
	@Override
	public void deactivateUser(Long id) {
	    for (User user : users) {
	        if (user.getId().equals(id)) {
	            user.setActive(false);
	            return;
	        }
	    }
	    throw new NoSuchElementException("User not found");
	}

	@Override
	public int getActiveStudentsCount() {
		int count = 0;
	    for (User user : users) {
	        if (user.isActive() && user.getRole().equalsIgnoreCase("Student")) {
	            count = count + 1;
	        }
	    }
	    return count;
	}
	
	@Override
	public List<User> getUsersByRole(String role) {
		List<User> result = new ArrayList<>();
		for (User user : users) {
			if (user.getRole().equalsIgnoreCase(role)) {
	            result.add(user);
	        }
		}
		return result;
	}

}