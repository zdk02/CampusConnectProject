package com.example.demo.models;

public class Club {
	
	private Long id; //long can hold more numbers than int
	private String name;
    private String description;
    private Long managerId;
    
    //Constructors
    public Club() {}
    
    public Club(Long id, String name, String description, Long managerId) {
		this.id = id;
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
	public void setId(Long id) {
		this.id = id;
	}
	
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