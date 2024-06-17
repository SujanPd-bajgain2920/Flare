package controller.servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import controller.DatabaseController;
import model.ProductModel;
import util.StringUtils;

/**
 * Servlet implementation class ModifyProductServlet
 */
@WebServlet("/ModifyProductServlet")
@MultipartConfig()
public class ModifyProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DatabaseController dbController = new DatabaseController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String updateId = request.getParameter("updateId");
		String deleteId = request.getParameter("deleteId");
		
		System.out.println(updateId);
		
		
		
		
		if (updateId != null && !updateId.isEmpty()) {
			doPut(request, response);
		}
		if (deleteId != null && !deleteId.isEmpty()) {
			doDelete(request, response);
		}
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
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Retrieve parameters from the request
	    String updateId = request.getParameter("updateId");
	    String productName = request.getParameter(StringUtils.PRODUCT_NAME);
	    String brandName = request.getParameter(StringUtils.BRAND_NAME);
	    String priceStr = request.getParameter(StringUtils.PRICE);
	    String stockStr = request.getParameter(StringUtils.STOCK);
	    String autoFocus = request.getParameter(StringUtils.AUTO_FOCUS);
	    String builtinFlash = request.getParameter(StringUtils.BUILT_IN_FLASH);
	    String type = request.getParameter(StringUtils.CAMERA);
	    String lens = request.getParameter(StringUtils.LENS);
	    String sensorSize = request.getParameter(StringUtils.SENSOR_SIZE);
	    String movieFormat = request.getParameter(StringUtils.MOVIE_FORMAT);
	    Part imagePart = request.getPart(StringUtils.IMAGE);
	    // Parse price as float
	    float price = Float.parseFloat(priceStr);
	    
	    // Parse stock as integer
	    int stock = Integer.parseInt(stockStr);
	    
	    String imageUrl = getImageUrl(imagePart);

	    // Create a ProductModel object with the updated information
	    ProductModel updateProduct = new ProductModel();
	    updateProduct.setProductId(Integer.parseInt(updateId));
	    updateProduct.setProductName(productName);
	    updateProduct.setBrandName(brandName);
	    updateProduct.setProductPrice(price);
	    updateProduct.setProductStock(stock);
	    updateProduct.setAutoFocus(autoFocus);
	    updateProduct.setProductFlash(builtinFlash);
	    updateProduct.setProductType(type);
	    updateProduct.setProductLens(lens);
	    updateProduct.setSensorSize(sensorSize);
	    updateProduct.setMovieFormat(movieFormat);
	    updateProduct.setImageUrlFromPart(imageUrl);
	    
	 // Save image file
        String savePath = StringUtils.IMAGE_DIR_PATH;
        String fileName = updateProduct.getImageUrlFromPart();
        if (!fileName.isEmpty()) {
            imagePart.write(savePath + fileName);
        }
	    
	    // Update product information in the database
	    int result = dbController.updateProdcut(updateProduct);
	    if (result == 1) {
	        // Product update successful
	        System.out.println("Product update successful");
	        request.setAttribute(StringUtils.SUCCESS_MESSAGE, "Product update successful");
	        response.sendRedirect(request.getContextPath() + StringUtils.ADMIN_PRODUCT_SERVLET);
	    } else {
	        // Product update failed
	        System.out.println("Product update failed");
	        request.setAttribute(StringUtils.ERROR_MESSAGE, "Product update failed");
	        response.sendRedirect(request.getContextPath() + StringUtils.ADMIN_PRODUCT_SERVLET);
	    }
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Delete Trigerred");
		int deleteId = Integer.parseInt(request.getParameter("deleteId"));
		if(dbController.deleteProduct(deleteId) == 1 ) {
			request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.SUCCESS_DELETE_MESSAGE);
			response.sendRedirect(request.getContextPath() + StringUtils.ADMIN_PRODUCT_SERVLET);
		}
		else {
			request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.ERROR_DELETE_MESSAGE);
			response.sendRedirect(request.getContextPath() + StringUtils.ADMIN_PRODUCT_SERVLET);
		}
	}
}