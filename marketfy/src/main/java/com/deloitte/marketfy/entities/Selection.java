package com.deloitte.marketfy.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_SELECTION")
public class Selection {
	
	@Id
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "user_id", nullable = false)
	private int userId;
	
	@Column(name = "product_id", nullable = false)
	private int productId;
	
	@Column(name = "product_quantity", nullable = false)
	private int productQuantity;

	@Column(name = "stored_in", length = 8)
	private String storedIn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getStoredIn() {
		return storedIn;
	}

	public void setStoredIn(String storedIn) {
		this.storedIn = storedIn;
	}
}
