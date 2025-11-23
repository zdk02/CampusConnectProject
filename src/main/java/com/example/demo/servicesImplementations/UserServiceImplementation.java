package com.example.demo.servicesImplementations;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;

@Service 
public class UserServiceImplementation implements UserService{
	
	private final UserRepository userRepository;
	
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	@Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
	
	@Override
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }
	
	@Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }
	
	@Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found with username: " + username));
    }
	
	@Override
    public int getActiveStudentsCount() {
        return userRepository.countByRoleAndActive(Role.STUDENT, true);
    }
	
	@Override
    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id);
        
        if (updatedUser.getUsername() != null) {
            existingUser.setUsername(updatedUser.getUsername());
        }
        if (updatedUser.getPassword() != null) {
            existingUser.setPassword(updatedUser.getPassword());
        }
        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getFullName() != null) {
            existingUser.setFullName(updatedUser.getFullName());
        }
        if (updatedUser.getRole() != null) {
            existingUser.setRole(updatedUser.getRole());
        }
        
        return userRepository.save(existingUser);
    }
	
	@Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
	
	@Override
    public void deactivateUser(Long id) {
        User user = getUserById(id);
        user.setActive(false);
        userRepository.save(user);
    }
	
	@Override
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElse(null);
        
        if (user == null) {
            return null; 
        }
        if (!user.getPassword().equals(password)) {
            return null;
        }
        if (!user.isActive()) {
        	return null;
        }
        return user;
    }

}