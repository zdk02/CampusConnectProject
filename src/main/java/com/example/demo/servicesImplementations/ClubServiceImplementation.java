package com.example.demo.servicesImplementations;
import org.springframework.stereotype.Service;
import com.example.demo.models.Club;
import com.example.demo.repositories.ClubRepository;
import com.example.demo.services.ClubService;
import java.util.List;
import java.util.NoSuchElementException;

@Service 
public class ClubServiceImplementation implements ClubService{

	private final ClubRepository clubRepository;
	
	public ClubServiceImplementation(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

	@Override
    public Club createClub(Club club) {
        //prevent duplicate club names
        if (clubRepository.existsByName(club.getName())) {
            throw new RuntimeException("A club with this name already exists");
        }
        return clubRepository.save(club);
    }

	@Override
	public List<Club> getAllClubs() {
	    return clubRepository.findAll();
	}
	
	@Override
	public Club updateClub(Long id, Club updatedClub) {
	    Club existingClub = clubRepository.findById(id)
	         .orElseThrow(() -> new NoSuchElementException("Club not found"));

	    existingClub.setName(updatedClub.getName());
	    existingClub.setDescription(updatedClub.getDescription());
	    existingClub.setManagerId(updatedClub.getManagerId());

	    return clubRepository.save(existingClub);
	}

	@Override
    public void deleteClub(Long id) {
        if (!clubRepository.existsById(id)) {
            throw new NoSuchElementException("Club not found");
        }
        clubRepository.deleteById(id);
    }
	
	@Override
    public Club getClubById(Long id) {
        return clubRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Club not found"));
    }
	
	@Override
    public List<Club> searchClubsByName(String name) {
        return clubRepository.findByNameContainingIgnoreCase(name);
    }
	
	@Override
    public int getClubsCount() {
        return (int) clubRepository.count();
    }
}