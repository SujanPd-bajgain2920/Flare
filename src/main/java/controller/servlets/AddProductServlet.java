package controller.servlets;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class AddProductServlet
 */
@WebServlet(name = "AddProductServlet", urlPatterns = {StringUtils.ADD_PRODUCT_SERVLET})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB)
public class AddProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private DatabaseController dbController;

    public void init() throws ServletException {
        super.init();
        dbController = new DatabaseController();
    }

  

    // to post in the database4
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Retrieve form parameters
    	 //int productId =  Integer.parseInt(request.getParameter(StringUtils.PRODUCT_ID));
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
        
        // Validate and parse numeric parameters
        float price;
        int stock;
        try {
            price = Float.parseFloat(priceStr);
            stock = Integer.parseInt(stockStr);
        } catch (NumberFormatException e) {
            // Handle invalid numeric input
            request.setAttribute(StringUtils.ERROR_MESSAGE, "Invalid price or stock format. Please enter valid numbers.");
            request.getRequestDispatcher(StringUtils.ADD_PRODUCT_PAGE).forward(request, response);
            return;
        }
        
        // Create ProductModel instance
        ProductModel productModel = new ProductModel(productName, brandName, price, stock, autoFocus, builtinFlash, type, lens
                , sensorSize, movieFormat, imagePart);
        
        // Save image file
        String savePath = StringUtils.IMAGE_DIR_PATH;
        String fileName = productModel.getImageUrlFromPart();
        if (!fileName.isEmpty()) {
            imagePart.write(savePath + fileName);
        }
        
        // Add product to database
        int result = dbController.addProduct(productModel);
        
        // Handle result
        if (result > 0) {
            request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.PRODUCT_ADD_SUCCESS_MESSAGE);
            response.sendRedirect(request.getContextPath() + StringUtils.ADD_PRODUCT_PAGE);
        } else {
            request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.SERVER_ERROR_MESSAGE);
            request.getRequestDispatcher(StringUtils.ADD_PRODUCT_PAGE).forward(request, response);
        }
    }
}
