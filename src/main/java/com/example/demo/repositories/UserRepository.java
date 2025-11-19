package com.example.demo.repositories;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.Role;
import com.example.demo.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	// Find user by username (for login)
	Optional<User> findByUsername(String username);
	
	// Find users by role (Student, Club Manager, Admin)
	List<User> findByRole(Role role);
	
	// Find all active users
	List<User> findByActive(boolean active);
	
    // Count active students (for admin statistics)
    int countByRoleAndActive(Role role, boolean active);
    
    // Check if username exists (for duplicate validation)
    boolean existsByUsername(String username);
}