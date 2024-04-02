package com.deloitte.marketfy.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.marketfy.entities.User;
import com.deloitte.marketfy.repositories.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	
	/////////////////////////////////---START SET UP---/////////////////////////////////
	
	private UserRepository userRepository;
	
	UserController(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	/////////////////////////////////---END SET UP---/////////////////////////////////
	
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
	
	/////////////////////////////////---END 'GET' OPERATIONS---/////////////////////////////////


}
