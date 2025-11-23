package com.example.demo.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; //primary key, auto-increment
	
	@NotBlank(message = "Username is required")
	@Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
	@Column(unique = true, nullable = false, length = 50)
	private String username;
	
	@NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Column(nullable = false, length = 255)
    private String password;

	@NotBlank(message = "Full name is required")
	@Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
	@Column(nullable = false, length = 100)
	private String fullName;
	
	@Email(message = "Email should be valid")
    @Column(unique = true, length = 100)
    private String email;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "Role is required")
	@Column(nullable = false, length = 20)
	private Role role;//"Student", "Club Manager", "Admin"

	@Column(nullable = false)
	private boolean active = true;
	
	//Constructors
	public User() {}
	
	public User(String username, String password, String fullName, String email, Role role, boolean active) {
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
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

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public String getFullName() {
		return fullName;
	}

	public String getEmail() {
        return email;
    }
	
	public Role getRole() {
		return role;
	}

	public boolean isActive() {
		return active;
	}

	//Setters
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public void setEmail(String email) {
        this.email = email;
    }
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
}