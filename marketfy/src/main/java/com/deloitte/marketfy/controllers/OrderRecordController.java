package com.deloitte.marketfy.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.marketfy.entities.OrderRecord;
import com.deloitte.marketfy.repositories.OrderRecordRepository;


@RestController
@RequestMapping("/api")
public class OrderRecordController {
	
	/////////////////////////////////---START 'SET UP'---/////////////////////////////////
	private OrderRecordRepository orderRecordRepository;
	
	OrderRecordController(OrderRecordRepository orderRepository){
		this.orderRecordRepository = orderRepository;
	}
	
	/////////////////////////////////---END 'SET UP'---/////////////////////////////////
	
	/////////////////////////////////---START 'GET' OPERATIONS---/////////////////////////////////
	
	@GetMapping("/orders")
	public List<OrderRecord> getAllRecords() {
		return orderRecordRepository.findAll();
	}
	
	@GetMapping("/orders/{recordId}")
	public Optional<OrderRecord> getSingleOrderRecordById(@PathVariable("recordId") int recordId) {
		return orderRecordRepository.findById(recordId);
	}
	
	@GetMapping("/users/{userId}/orders")
	public List<OrderRecord> getUserOrders(@PathVariable("userId") int userId){
		return orderRecordRepository.getUserOrders(userId);
	}
	
	@GetMapping("/users/{userId}/orders/{orderId}")
	public List<OrderRecord> getOrder(@PathVariable("userId") int userId, @PathVariable("orderId") String orderId){
		return orderRecordRepository.getOrder(userId, orderId);
	}
	
	/////////////////////////////////---END 'GET' OPERATIONS---/////////////////////////////////
	
	/////////////////////////////////---START 'POST' OPERATIONS---/////////////////////////////////
	@PostMapping("/orders")
	public void saveSingleOrderRecord(@RequestBody OrderRecord order) {
		orderRecordRepository.save(order);
	}
	
	/////////////////////////////////---END 'POST' OPERATIONS---/////////////////////////////////
	
	/////////////////////////////////---START 'PATCH' OPERATIONS---/////////////////////////////////
	@PatchMapping("/orders/{recordId}")
	public ResponseEntity<String> updateRecord(@PathVariable("recordId") int recordId, @RequestBody OrderRecord updatedOrderRecord) {
		Optional<OrderRecord> orderRecordInDB = getSingleOrderRecordById(recordId);
		
		if(orderRecordInDB.isEmpty()) {
			return new ResponseEntity<String>("RECORD NOT FOUND", HttpStatus.NOT_FOUND);
		} else {
			orderRecordInDB.ifPresent(orderRecord -> {
				if(updatedOrderRecord.getOrderDate() != null) {
					orderRecord.setOrderDate(updatedOrderRecord.getOrderDate());
				}
				if(updatedOrderRecord.getProductPrice() > 0) {
					orderRecord.setProductPrice(updatedOrderRecord.getProductPrice());
				}
				if(updatedOrderRecord.getProductQuantity() > 0) {
					orderRecord.setProductQuantity(updatedOrderRecord.getProductQuantity());
				}
				orderRecordRepository.save(orderRecord);
			});	
		}
		return new ResponseEntity<String>("RECORD UPDATED", HttpStatus.OK);
	}
	
	/////////////////////////////////---END 'PATCH' OPERATIONS---/////////////////////////////////
	
	/////////////////////////////////---START 'DELETE' OPERATIONS---/////////////////////////////////
	
	@DeleteMapping("/orders/delete/{orderRecordId}")
	public void deleteOrderRecord(@PathVariable("orderRecordId") int orderRecordId) {
		orderRecordRepository.deleteById(orderRecordId);
	}
	
	/////////////////////////////////---END 'DELETE' OPERATIONS---/////////////////////////////////
}