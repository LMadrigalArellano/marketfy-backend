package com.deloitte.marketfy.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.marketfy.entities.Product;
import com.deloitte.marketfy.repositories.ProductRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ProductController {
	

	/////////////////////////////////---START 'SET UP'---/////////////////////////////////
	private ProductRepository productRepository;
	
	ProductController(ProductRepository productRepository){
		this.productRepository = productRepository;
	}

	/////////////////////////////////---END 'SET UP'---/////////////////////////////////
	
	/////////////////////////////////---START 'GET' OPERATIONS---/////////////////////////////////
//	@GetMapping("/products")
//	public List<Product> getAllProducts(){
//		return productRepository.findAll();
//	}
//	
	@GetMapping("/products")
	public Page<Product> getPaginatedProducts(@RequestParam Map<String, String> queryParameters){
		
		String stringPageIndex = queryParameters.get("pageIndex");
		String stringPageSize = queryParameters.get("pageSize");
		String sortBy = queryParameters.get("sortBy");
		
		int pageIndex = 0;
		int pageSize = 9;
		
		if(stringPageIndex != null) {
			pageIndex = Integer.valueOf(stringPageIndex);
		}
		if(stringPageSize != null) {
			pageSize = Integer.valueOf(stringPageSize);
		}
		if(sortBy == null) {
			sortBy = "productId";
		}

		return productRepository.findAll(PageRequest.of(pageIndex, pageSize, Sort.by(sortBy)));
	}
	
	@GetMapping("/products/{productId}")
	public Optional<Product> getProductById(@PathVariable("productId") int productId){
		
		Optional<Product> product = productRepository.findById(productId);
		return product;	
	}
	
	@GetMapping("/products/title/{title}")
	public List<Product> getProductByTitle(@PathVariable("title") String title){
		return productRepository.findByTitle(title);
	}
	
	@GetMapping("/products/price")
	public List<Product> getProductByPriceRange(@RequestParam("minPrice") double minPrice, @RequestParam("maxPrice") double maxPrice){
		return productRepository.findByPriceRange(minPrice, maxPrice);
	}

	
	/////////////////////////////////---END 'GET' OPERATIONS---/////////////////////////////////
	
	/////////////////////////////////---START 'POST' OPERATIONS---/////////////////////////////////
	@PostMapping("/products")
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		
		ResponseEntity<String> result;
		Optional<Product> productInDB = productRepository.findOneByTitle(product.getTitle());
		
		if(productInDB.isPresent()) {
			Product existingProduct = productInDB.get();
			existingProduct.setTotalInInventory(existingProduct.getTotalInInventory() + 1);
			productRepository.save(existingProduct);
			result = new ResponseEntity<>("PRODUCT \""+product.getTitle()+"\" TOTAL INVENTORY INCREASED BY 1", HttpStatus.OK);	
			
		} else {
			productRepository.save(product);
			result = new ResponseEntity<>("PRODUCT \""+product.getTitle()+"\" ADDED", HttpStatus.CREATED);	

		}
		
		return result;
	}
	

	/////////////////////////////////---END 'POST' OPERATIONS---/////////////////////////////////
	
	/////////////////////////////////---START 'PATCH' OPERATIONS---/////////////////////////////////
	
	@PatchMapping("/products/{productId}")
	public ResponseEntity<String> updateProduct(
			@PathVariable("productId") int productId, 
			@RequestBody Product updatedProduct
	){
		ResponseEntity<String> result =  new ResponseEntity<>("PRODUCT WITH ID \""+productId+"\" DOES NOT EXIST", HttpStatus.NOT_FOUND);
		Optional<Product> productInDB = getProductById(productId);
		
		if(productInDB.isPresent()) {
			Product existingProduct = productInDB.get();
			existingProduct.setTitle(updatedProduct.getTitle());
			existingProduct.setPrice(updatedProduct.getPrice());
			existingProduct.setImage(updatedProduct.getImage());
			existingProduct.setDescription(updatedProduct.getDescription());
			existingProduct.setTotalInInventory(updatedProduct.getTotalInInventory());
			productRepository.save(existingProduct);
			result = new ResponseEntity<>("PRODUCT WITH ID \""+productId+"\" UPDATED", HttpStatus.OK);	
		}
		
		return result;
	}

	
	/////////////////////////////////---END 'PATCH' OPERATIONS---/////////////////////////////////
	
	/////////////////////////////////---START 'DELETE' OPERATIONS---/////////////////////////////////
	@DeleteMapping("/products/delete/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") int productId){
		ResponseEntity<String> result =  new ResponseEntity<>("PRODUCT WITH ID \""+productId+"\" DOES NOT EXIST", HttpStatus.NOT_FOUND);
		Optional<Product> productInDB = getProductById(productId);
		
		if(productInDB.isPresent()) {
			Product existingProduct = productInDB.get();
			productRepository.delete(existingProduct);
			result = new ResponseEntity<>("PRODUCT WITH ID \""+productId+"\" DELETED", HttpStatus.OK);	
		}
		
		return result;
	}

	/////////////////////////////////---END 'DELETE' OPERATIONS---/////////////////////////////////

}
