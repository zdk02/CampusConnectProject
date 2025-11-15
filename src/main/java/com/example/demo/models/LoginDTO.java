package com.example.demo.models;

public class LoginDTO {

	private String username;
    private String password;
    
    //Constructor
    public LoginDTO() {}
    
    //Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    
    //Setters
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    
}