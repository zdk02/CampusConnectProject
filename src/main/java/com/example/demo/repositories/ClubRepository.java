package com.example.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.Club;
import java.util.List;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long>{

	// Search clubs by name
	List<Club> findByNameContainingIgnoreCase(String name);
	
	// Find all clubs managed by a specific manager
	List<Club> findByManagerId(Long managerId);
	
	// Check if a club with this name already exists
	boolean existsByName(String name);
	
	// Find clubs by exact name (case-insensitive)
	Optional<Club> findByNameIgnoreCase(String name);
}