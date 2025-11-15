package com.example.demo.servicesImplementations;
import org.springframework.stereotype.Service;

import com.example.demo.models.Club;
import com.example.demo.services.ClubService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service 
public class ClubServiceImplementation implements ClubService{

	private final List<Club> clubs = new ArrayList<>();
	
	@Override
	public Club createClub(Club club) {
		for (Club c : clubs) {
		    if (c.getId().equals(club.getId())) {
		        throw new RuntimeException("Club ID already exists");
		    }
		}
		clubs.add(club);
		return club;
	}

	@Override
	public List<Club> getAllClubs() {
		return new ArrayList<>(clubs);
	}
	
	@Override
	public Club updateClub(Long id, Club updatedClub) {
		for (Club club : clubs) {
			if (club.getId().equals(id)) {
				club.setName(updatedClub.getName());
				club.setDescription(updatedClub.getDescription());
				club.setManagerId(updatedClub.getManagerId());
				return club;
			}
		}
		throw new NoSuchElementException("Club not found");
	}
	
	@Override
	public void deleteClub(Long id) {
		boolean removed = clubs.removeIf(club -> club.getId().equals(id));
	    if (!removed) {
	        throw new NoSuchElementException("Club not found");
	    }
	}
	
	@Override
	public Club getClubById(Long id) {
		for (Club club : clubs) {
			if (club.getId().equals(id)) {
	            return club;
	        }
		}
		throw new NoSuchElementException("Club not found");
	}
	
	@Override
	public List<Club> searchClubsByName(String name){
		List<Club> result = new ArrayList<>();
		for (Club club : clubs) {
			if (club.getName().toLowerCase().contains(name.toLowerCase())) {
				result.add(club);
	        }
		}
		return result;
	}
	
	@Override
	public int getClubsCount() {
		return clubs.size();
	}
}