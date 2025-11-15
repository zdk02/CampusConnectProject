package com.example.demo.services;
import java.util.List;

import com.example.demo.models.Club;

public interface ClubService {

	//Only Club Managers can create new clubs
	public Club createClub(Club club);
	
	//Club Managers can update the clubs they manage
	//Admins can update any club
	public Club updateClub(Long id, Club updatedClub);
	
	//Only Admins can delete any club
    public void deleteClub(Long id);
    
    //All users (Student, Manager, Admin) can view/search clubs
	public List<Club> getAllClubs();
    public Club getClubById(Long id);
    public List<Club> searchClubsByName(String name);
    
    //Admin can view the number of clubs
    public int getClubsCount();
}