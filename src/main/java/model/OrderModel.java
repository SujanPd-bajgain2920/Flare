package model;

import java.sql.Date;

public class OrderModel {

	private int orderId;
	private String productName;
	private Date orderDate;
	private String status;
	private int cartId;
	private int productId;
	private int userId;
	private int quantity;
	private int price; // Changed capitalization
	private int totalPrice;

	// Constructor
	public OrderModel(int orderId, Date orderDate, String status, int cartId, int productId, int userId) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.status = status;
		this.cartId = cartId;
		this.productId = productId;
		this.userId = userId;
	}

	public OrderModel() {
		// TODO Auto-generated constructor stub
	}

	// Getters and setters
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price; // Changed capitalization
	}

	public void setPrice(int price) {
		this.price = price; // Changed capitalization
	}
}
