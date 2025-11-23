package com.example.demo.servicesImplementations;
import org.springframework.stereotype.Service;
import com.example.demo.models.Club;
import com.example.demo.models.User;
import com.example.demo.repositories.ClubRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.ClubService;
import java.util.List;
import java.util.NoSuchElementException;

@Service 
public class ClubServiceImplementation implements ClubService{

	private final ClubRepository clubRepository;
	private final UserRepository userRepository;
	
	public ClubServiceImplementation(ClubRepository clubRepository, UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }
	
	@Override
    public Club createClub(Club club, Long managerId) {
        // Prevent duplicate club names
        if (clubRepository.existsByName(club.getName())) {
            throw new RuntimeException("A club with this name already exists");
        }
        
        // Get the manager and link to the club
        User manager = userRepository.findById(managerId)
                .orElseThrow(() -> new NoSuchElementException("Manager not found"));
        
        club.setManager(manager);
        return clubRepository.save(club);
    }
	
	@Override
    public Club updateClub(Long id, Club updatedClub) {
        Club existingClub = clubRepository.findById(id)
             .orElseThrow(() -> new NoSuchElementException("Club not found with id: " + id));

        // Update only the fields that are provided
        if (updatedClub.getName() != null) {
            existingClub.setName(updatedClub.getName());
        }
        if (updatedClub.getDescription() != null) {
            existingClub.setDescription(updatedClub.getDescription());
        }

        return clubRepository.save(existingClub);
    }
	
	@Override
    public void deleteClub(Long id) {
        if (!clubRepository.existsById(id)) {
            throw new NoSuchElementException("Club not found with id: " + id);
        }
        clubRepository.deleteById(id);
    }
	
	@Override
    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }
	
	@Override
    public Club getClubById(Long id) {
        return clubRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Club not found with id: " + id));
    }
	
	@Override
	public List<Club> searchClubsByName(String name) {
	        return clubRepository.findByNameContainingIgnoreCase(name);
	}
	
	@Override
    public int getClubsCount() {
        return (int) clubRepository.count();
    }
	
	@Override
    public List<Club> getClubsByManager(Long managerId) {
        return clubRepository.findByManagerId(managerId);
    }
}