package com.deloitte.marketfy.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ORDER_HISTORY")
public class OrderRecord {
	
	@Id
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "order_id", nullable = false, length = 36)
	private String orderId;
	
	@Column(name = "order_date", nullable = false)
	private Date orderDate;
	
	@Column(name = "user_id", nullable = false)
	private int userId;
	
	@Column(name = "product_id", nullable = false)
	private int productId;
	
	@Column(name = "product_price", nullable = false)
	private double productPrice;
	
	@Column(name = "product_quantity", nullable = false)
	private int productQuantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

}
