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
    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        return userRepository.save(user);
    }
	
	@Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

	@Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }
	
	@Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
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
    public int getActiveStudentsCount() {
        return userRepository.countByRoleAndActive(Role.STUDENT, true);
    }

	@Override
    public void deactivateUser(Long id) {
        User user = getUserById(id);
        user.setActive(false);
        userRepository.save(user);
    }
	
	@Override
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

}