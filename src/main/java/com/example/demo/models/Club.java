package com.example.demo.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "clubs")
public class Club {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; //long can hold more numbers than int
	
	@NotBlank(message = "Club name is required")
    @Size(min = 3, max = 100, message = "Club name must be between 3 and 100 characters")
    @Column(nullable = false, length = 100)
	private String name;
	
	@NotBlank(message = "Description is required")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    @Column(nullable = false, length = 1000)
    private String description;
	
	@NotNull(message = "Manager ID is required")
	@Positive(message = "Manager ID must be a positive number")
    @Column(nullable = false)
    private Long managerId;
    
    //Constructors
    public Club() {}
    
    public Club(String name, String description, Long managerId) {
		this.name = name;
		this.description = description;
		this.managerId = managerId;
	}

    //Getters
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Long getManagerId() {
		return managerId;
	} 
    
	//Setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
    
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
}