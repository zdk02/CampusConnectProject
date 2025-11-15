package com.example.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Club;
import com.example.demo.services.ClubService;

import java.util.List;

@RestController
@RequestMapping("/clubs")
public class ClubController {

	 @Autowired //it injects an existing bean into my class
	 private ClubService clubService;
	
	//Create Club
	@PostMapping("/create")
	public Club createClub(@RequestBody Club club) {

		Club savedClub = clubService.createClub(club);
		return savedClub;
	}
	
	//Get All Clubs
    @GetMapping
    public List<Club> getAllClubs() {
        return clubService.getAllClubs();
    }
    
    //Search Clubs by name
    @GetMapping("/search")
    public List<Club> searchClubs(@RequestParam String name) {
        return clubService.searchClubsByName(name);
    }

    //Get Club by ID
    @GetMapping("/{id}")
    public Club getClubById(@PathVariable Long id) {
        return clubService.getClubById(id);
    }

    //Update Club
    @PutMapping("/{id}")
    public Club updateClub(@PathVariable Long id,
    					   @RequestBody Club updatedClub) {
    	updatedClub.setId(id);
        return clubService.updateClub(id, updatedClub);
    }

    //Delete Club
    @DeleteMapping("/{id}")
    public String deleteClub(@PathVariable Long id) {
        clubService.deleteClub(id);
        return "Club deleted successfully";
    }
    
    //Get Clubs Count (Admin)
    @GetMapping("/count")
    public int getClubsCount() {
        return clubService.getClubsCount();
    }
}

//later should put admin endpoints under /admin