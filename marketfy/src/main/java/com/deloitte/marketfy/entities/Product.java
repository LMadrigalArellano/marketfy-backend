package com.deloitte.marketfy.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCTS")
public class Product {
	
	@Id
	@Column(name = "product_id", nullable = false, precision = 10)
	private int productId;
	
	@Column(name = "title", nullable = false, length = 100)
	private String title;
	
	@Column(name = "price", nullable = false)
	private double price;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "description", length = 200)
	private String description;
	
	@Column(name = "total_in_inventory", nullable = false)
	private int totalInInventory;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTotalInInventory() {
		return totalInInventory;
	}

	public void setTotalInInventory(int totalInInventory) {
		this.totalInInventory = totalInInventory;
	}

}
