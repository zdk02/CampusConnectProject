package com.example.demo.models;

public class User {

	private Long id;
	private String username;
	private String password;
	private String fullName;
	private String role;     //"Student", "Club Manager", "Admin"
	private boolean active;
	
	//Constructors
	public User() {}
	
	public User(Long id, String username, String password, String fullName, String role, boolean active) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.role = role;
		this.active = active;
	}

	//Getters
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFullName() {
		return fullName;
	}

	public String getRole() {
		return role;
	}

	public boolean isActive() {
		return active;
	}

	//Setters
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
}