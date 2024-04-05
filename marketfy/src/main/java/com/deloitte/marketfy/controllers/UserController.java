package com.deloitte.marketfy.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.marketfy.entities.User;
import com.deloitte.marketfy.repositories.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UserController {
	
	/////////////////////////////////---START 'SET UP'---/////////////////////////////////
	
	private UserRepository userRepository;
	
	UserController(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	/////////////////////////////////---END 'SET UP'---/////////////////////////////////
	
	/////////////////////////////////---START 'GET' OPERATIONS---/////////////////////////////////
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{userId}")
	public Optional<User> getUserById(@PathVariable("userId") int userId) {
		Optional<User> user = userRepository.findById(userId);		
		return user;
	}
	
	@GetMapping("/users/email/{email}")
	public Optional<User> getUserByEmail(@PathVariable("email") String email) {		
		Optional<User> user = userRepository.findByEmail(email);
		return user;
	}
	
	@PostMapping("/users/login")
	public Optional<User> validateLogin(@RequestBody Map<String, String> userLoginData){
		
		String email = userLoginData.get("email");
		String password = userLoginData.get("password");

		
		if(email != null && password != null) {
			Optional<User> userInDB = userRepository.findByEmail(email);
			
			if(userInDB.isPresent()) {				
				if(userInDB.get().getPassword().equals(password)) {
					return userInDB;
				}
			}
		}
		return null;
	}
	
	/////////////////////////////////---END 'GET' OPERATIONS---/////////////////////////////////
	
	/////////////////////////////////---START 'POST' OPERATIONS---/////////////////////////////////

	@PostMapping("/users")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
	
		ResponseEntity<String> result = new ResponseEntity<String>("EMAIL \""+user.getEmail()+"\" IS ALREADY REGISTERED", HttpStatus.CONFLICT);	;
		boolean emailIsNotRegistered = getUserByEmail(user.getEmail()).isEmpty();
		
		if(emailIsNotRegistered) {
			result = new ResponseEntity<String>("USER \""+user.getFirstName()+"\" CREATED", HttpStatus.CREATED);	
			userRepository.save(user);
		}
		
		return result;
	}

	/////////////////////////////////---END 'POST' OPERATIONS---/////////////////////////////////
	
	/////////////////////////////////---START 'PATCH' OPERATIONS---/////////////////////////////////
	
	@PatchMapping("users/{userId}")
	public ResponseEntity<String> updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
		
		ResponseEntity<String> result = new ResponseEntity<>("USER WITH ID \""+userId+"\" UPDATED", HttpStatus.OK);
		Optional<User> userInDB = getUserById(userId);
		boolean isNewEmailAvailable = getUserByEmail(newUser.getEmail()).isEmpty();
		
		if(userInDB.isEmpty()) {
			return new ResponseEntity<String>("USER WITH ID \""+userId+"\" NOT FOUND", HttpStatus.NOT_FOUND);
		
		} else if(!isNewEmailAvailable) {
			return new ResponseEntity<String>("EMAIL \""+newUser.getEmail()+"\" ALREADY REGISTERED", HttpStatus.CONFLICT);

		} else {
			userInDB.ifPresent(user -> {
				if(newUser.getFirstName() != null) {
					user.setFirstName(newUser.getFirstName());
				}
				if(newUser.getLastName() != null) {
					user.setLastName(newUser.getLastName());
				}
				if(newUser.getBio() != null) {
					user.setBio(newUser.getBio());
				}
				if(newUser.getEmail() != null) {
					user.setEmail(newUser.getEmail());
				}
				if(newUser.getPassword() != null) {
					user.setPassword(newUser.getPassword());
				}
				if(newUser.getAreasOfInterest() != null) {
					user.setAreasOfInterest(newUser.getAreasOfInterest());	
				}
			
				userRepository.save(user);
			});	
		}
	
		return result;
	}
	
	/////////////////////////////////---END 'PATCH' OPERATIONS---/////////////////////////////////
	
	/////////////////////////////////---START 'DELETE' OPERATIONS---/////////////////////////////////

	@DeleteMapping("/users/delete/{userId}")
	public ResponseEntity<String> deleteUserById(@PathVariable("userId") int userId){
		
		ResponseEntity<String> result = new ResponseEntity<String>("USER WITH ID \""+userId+"\" NOT FOUND", HttpStatus.NOT_FOUND);
		
		if(getUserById(userId).isPresent()) {
			userRepository.deleteById(userId);
			result = new ResponseEntity<String>("USER WITH ID \""+userId+"\" DELETED", HttpStatus.OK);	
		}
		
		return result;		
	}
	
	/////////////////////////////////---END 'DELETE' OPERATIONS---/////////////////////////////////

}
