package model;

import java.io.File;

import javax.servlet.http.Part;

public class ProductModel {
	
	private int productId;
    private String productName;
    private String brandName;
    private float productPrice;
    private int productStock;
    private String autoFocus;
    private String productFlash;
    private String productType;
    private String productLens;
    private String sensorSize;
    private String movieFormat;
    private String imageUrlFromPart;
    
	/**
	 * @param productId
	 * @param productName
	 * @param brandName
	 * @param price
	 * @param stock
	 * @param autoFocus
	 * @param flash
	 * @param type
	 * @param lens
	 * @param sensorSize
	 * @param movieFormat
	 * @param imageUrlFromPart
	 */
	public ProductModel(String productName, String brandName, float productPrice, int productStock, String autoFocus, String productFlash,
			String productType, String productLens, String sensorSize, String movieFormat, Part imagePart) {
		super();
		this.productName = productName;
		this.brandName = brandName;
		this.productPrice = productPrice;
		this.productStock = productStock;
		this.autoFocus = autoFocus;
		this.productFlash = productFlash;
		this.productType = productType;
		this.productLens = productLens;
		this.sensorSize = sensorSize;
		this.movieFormat = movieFormat;
		this.imageUrlFromPart = getImageUrl(imagePart);
	}
	
	public ProductModel(int productid, String productName, String brandName, float productPrice, int productStock, String autoFocus, String productFlash,
			String productType, String productLens, String sensorSize, String movieFormat, Part imagePart) {
		super();
		this.productId = productid;
		this.productName = productName;
		this.brandName = brandName;
		this.productPrice = productPrice;
		this.productStock = productStock;
		this.autoFocus = autoFocus;
		this.productFlash = productFlash;
		this.productType = productType;
		this.productLens = productLens;
		this.sensorSize = sensorSize;
		this.movieFormat = movieFormat;
		this.imageUrlFromPart = getImageUrl(imagePart);
	}
	

	public ProductModel() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}


	/**
	 * @param productName the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}


	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}



	/**
	 * @return the brandName
	 */
	public String getBrandName() {
		return brandName;
	}


	/**
	 * @param brandName the brandName to set
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}




	/**
	 * @return the price
	 */
	public float getProductPrice() {
		return productPrice;
	}



	/**
	 * @param price the price to set
	 */
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}



	/**
	 * @return the stock
	 */
	public int getProductStock() {
		return productStock;
	}



	/**
	 * @param stock the stock to set
	 */
	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}


	/**
	 * @return the autoFocus
	 */
	public String getAutoFocus() {
		return autoFocus;
	}


	/**
	 * @param autoFocus the autoFocus to set
	 */
	public void setAutoFocus(String autoFocus) {
		this.autoFocus = autoFocus;
	}


	/**
	 * @return the flash
	 */
	public String getProductFlash() {
		return productFlash;
	}


	/**
	 * @param flash the flash to set
	 */
	public void setProductFlash(String productFlash) {
		this.productFlash = productFlash;
	}


	/**
	 * @return the type
	 */
	public String getProductType() {
		return productType;
	}



	/**
	 * @param type the type to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}




	/**
	 * @return the lens
	 */
	public String getProductLens() {
		return productLens;
	}



	/**
	 * @param lens the lens to set
	 */
	public void setProductLens(String productLens) {
		this.productLens = productLens;
	}



	/**
	 * @return the sensorSize
	 */
	public String getSensorSize() {
		return sensorSize;
	}



	/**
	 * @param sensorSize the sensorSize to set
	 */
	public void setSensorSize(String sensorSize) {
		this.sensorSize = sensorSize;
	}




	/**
	 * @return the movieFormat
	 */
	public String getMovieFormat() {
		return movieFormat;
	}


	/**
	 * @param movieFormat the movieFormat to set
	 */
	public void setMovieFormat(String movieFormat) {
		this.movieFormat = movieFormat;
	}


	/**
	 * @return the imageUrlFromPart
	 */
	public String getImageUrlFromPart() {
		return imageUrlFromPart;
	}



	/**
	 * @param imageUrlFromPart the imageUrlFromPart to set
	 */
	public void setImageUrlFromPart(String imageUrlFromPart) {
		this.imageUrlFromPart = imageUrlFromPart;
	}


	private String getImageUrl(Part imagePart) {
		String savePath = "/home/arch/eclipse-workspace/Flare/src/main/webapp/resources/images";
		File fileSaveDir = new File(savePath);
		String imageUrlFromPart = null;
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		String contentDisp = imagePart.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				imageUrlFromPart = s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		if (imageUrlFromPart == null || imageUrlFromPart.isEmpty()) {
			imageUrlFromPart = "default.jpg";
		}
		return imageUrlFromPart;
	}
	
	
}
