package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseController;
import model.ProductModel;

/**
 * Servlet implementation class AdminProductDetailsServlet
 */
@WebServlet("/AdminProductDetailsServlet")
public class AdminProductDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DatabaseController dbController;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize DatabaseController properly
        dbController = new DatabaseController();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        if (productName != null && !productName.isEmpty()) {
            try {
                // Fetch details of the specified product from the database
                ProductModel product = dbController.getProductByName(productName);
                if (product != null) {
                    request.setAttribute("product", product);
                    request.getRequestDispatcher("pages/adminproductdetails.jsp").forward(request, response);
                    return; // Exit the method after forwarding the request
                } else {
                    System.out.println("No product found for productName: " + productName);
                }
            } catch (Exception e) {
                // Log any exceptions
                e.printStackTrace();
            }
        } else {
            System.out.println("No productName parameter provided in the request");
        }
        // Forward to an error page or handle the exception as needed
        request.setAttribute("error", "Invalid productName parameter");
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}