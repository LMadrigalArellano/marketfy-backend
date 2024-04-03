package com.deloitte.marketfy.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Users")
public class User {

	@Id
	@Column(name = "user_id", nullable = false)
	private int userId;
	
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;
	
	@Column(name = "last_name", length = 50)
	private String lastName;
	
	@Column(name = "bio", length = 200)
	private String bio;
	
	@Column(name = "email", nullable = false, length = 200)
	private String email;
	
	@Column(name = "password", nullable = false, length = 200)
	private String password;
	
	@Column(name = "areas_of_interest", length = 300)
	private String areasOfInterest;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAreasOfInterest() {
		return areasOfInterest;
	}

	public void setAreasOfInterest(String areasOfInterest) {
		this.areasOfInterest = areasOfInterest;
	}

	

}
