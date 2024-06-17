package model;

public class CartModel {
    private int cartId;
    private int ProductId;
    private int UserId;
    private String productName;
    private int productPrice;
    private String image;
    private int quantity;
    private int totalPrice;
    private String status;
	
    public CartModel(int cartId, int productId, int userId, String productName, int productPrice, String image,int quantity, int totalPrice, String status) {
		super();
		this.cartId = cartId;
		ProductId = productId;
		UserId = userId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.image = image;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.status = status;
	}
    
    public CartModel() {
		
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getProductId() {
		return ProductId;
	}

	public void setProductId(int productId) {
		ProductId = productId;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}