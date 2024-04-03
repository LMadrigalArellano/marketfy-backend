package com.deloitte.marketfy.controllers;

import java.util.List;
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

import com.deloitte.marketfy.entities.Selection;
import com.deloitte.marketfy.repositories.SelectionRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class SelectionController {

	/////////////////////////////////---START 'SET UP'---/////////////////////////////////
	private SelectionRepository selectionRepository;
	
	SelectionController(SelectionRepository selectionRepository){
		this.selectionRepository = selectionRepository;
	}
	
	/////////////////////////////////---END 'SET UP'---/////////////////////////////////
	
	/////////////////////////////////---START 'GET' OPERATIONS---/////////////////////////////////
	
	@GetMapping("/selection/{selectionId}")
	public Optional<Selection> getSelectionById(@PathVariable("selectionId") int selectionId) {
		return selectionRepository.findById(selectionId);
	}
	
	@GetMapping("/users/{userId}/wishlist")
	public List<Selection> getUserWishlist(@PathVariable("userId") int userId){
		return selectionRepository.getUserWishlist(userId);
	}
	
	@GetMapping("/users/{userId}/cart")
	public List<Selection> getUserCart(@PathVariable("userId") int userId){
		return selectionRepository.getUserCart(userId);
	}
	
	
	/////////////////////////////////---END 'GET' OPERATIONS---/////////////////////////////////
	
	/////////////////////////////////---START 'POST' OPERATIONS---/////////////////////////////////
	@PostMapping("/selection")
	public void saveSelection(@RequestBody Selection selection) {
		selectionRepository.save(selection);
	}

	
	/////////////////////////////////---END 'POST' OPERATIONS---/////////////////////////////////
	
	/////////////////////////////////---START 'PATCH' OPERATIONS---/////////////////////////////////
	@PatchMapping("/selection/{selectionId}")
	public ResponseEntity<String> updateSelection(@PathVariable("selectionId") int selectionId, @RequestBody Selection updatedSelection) {
		Optional<Selection> selectionInDB = getSelectionById(selectionId);
		
		if(selectionInDB.isEmpty()) {
			
			return new ResponseEntity<String>("RECORD NOT FOUND", HttpStatus.NOT_FOUND);
			
		} else {
			selectionInDB.ifPresent(selection -> {
				if(updatedSelection.getProductQuantity() != 0) {
					selection.setProductQuantity(updatedSelection.getProductQuantity());
				}
				if(updatedSelection.getStoredIn() != null) {
					selection.setStoredIn(updatedSelection.getStoredIn());
				}
				selectionRepository.save(selection);
			});	
		}
		return new ResponseEntity<String>("RECORD UPDATED", HttpStatus.OK);
	}
	
	
	
	/////////////////////////////////---END 'PATCH' OPERATIONS---/////////////////////////////////
	
	/////////////////////////////////---START 'DELETE' OPERATIONS---/////////////////////////////////

	@DeleteMapping("/selection/delete/{selectionId}")
	public void deleteSelection(@PathVariable("selectionId") int selectionId) {
		selectionRepository.deleteById(selectionId);
	}
	
	/////////////////////////////////---END 'DELETE' OPERATIONS---/////////////////////////////////
}
