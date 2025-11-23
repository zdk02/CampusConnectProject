package com.example.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.demo.models.Club;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.services.ClubService;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/clubs")
public class ClubController {

	@Autowired //it injects an existing bean into my class
	private ClubService clubService;
	
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

	private ResponseEntity<?> checkClubManager(Long managerId) {
        User manager = userService.getUserById(managerId);
        if (manager == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Manager not found");
        }
        if (manager.getRole() != Role.CLUB_MANAGER) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Only Club Managers can perform this action");
        }
        return null;
    }

	// Create Club (Club Manager only)
	@PostMapping("/create")
    public ResponseEntity<?> createClub(
            @RequestBody Club club, 
            @RequestParam Long managerId) {
        
        ResponseEntity<?> authCheck = checkClubManager(managerId);
        if (authCheck != null) return authCheck;
        
        Club savedClub = clubService.createClub(club, managerId);
        return ResponseEntity.ok(savedClub);
    }
	
	//Get All Clubs
	@GetMapping
    public ResponseEntity<?> getAllClubs() {
        return ResponseEntity.ok(clubService.getAllClubs());
    }
    
	//Search Clubs by name
    @GetMapping("/search")
    public ResponseEntity<?> searchClubs(@RequestParam String name) {
        return ResponseEntity.ok(clubService.searchClubsByName(name));
    }

    //Get Club by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getClubById(@PathVariable Long id) {
        Club club = clubService.getClubById(id);
        if (club == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Club not found");
        }
        return ResponseEntity.ok(club);
    }
    
    //Update Club (Club Manager of that club only)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClub(
            @PathVariable Long id, 
            @RequestBody Club updatedClub,
            @RequestParam Long requesterId) {
        
        User requester = userService.getUserById(requesterId);
        if (requester == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("User not found");
        }

        Club existingClub = clubService.getClubById(id);
        if (existingClub == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Club not found");
        }
        
        // Only the club's manager can update it
        if (requester.getRole() != Role.CLUB_MANAGER) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Only Club Managers can update clubs");
        }
        
        if (!existingClub.getManager().getId().equals(requesterId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("You can only update your own clubs");
        }
        
        return ResponseEntity.ok(clubService.updateClub(id, updatedClub));
    }
    
    //Delete Club (Admin only)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClub(
            @PathVariable Long id,
            @RequestParam Long requesterId) {
        
        ResponseEntity<?> authCheck = checkAdmin(requesterId);
        if (authCheck != null) return authCheck;
        
        clubService.deleteClub(id);
        return ResponseEntity.ok("Club deleted successfully");
    }
    
    // Get Clubs Count (Admin only)
    @GetMapping("/count")
    public ResponseEntity<?> getClubsCount(@RequestParam Long requesterId) {
        ResponseEntity<?> authCheck = checkAdmin(requesterId);
        if (authCheck != null) return authCheck;
        
        return ResponseEntity.ok(clubService.getClubsCount());
    }

    // Get clubs by manager (Club Manager only - to see their own clubs)
    @GetMapping("/my-clubs")
    public ResponseEntity<?> getMyClubs(@RequestParam Long managerId) {
        ResponseEntity<?> authCheck = checkClubManager(managerId);
        if (authCheck != null) return authCheck;
        
        return ResponseEntity.ok(clubService.getClubsByManager(managerId));
    }
}