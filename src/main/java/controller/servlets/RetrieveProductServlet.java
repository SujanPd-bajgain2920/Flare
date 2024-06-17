package controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseController;
import model.ProductModel;
import util.StringUtils;

/**
 * Servlet implementation class RetrieveProduct
 */
@WebServlet("/RetrieveProductServlet")
public class RetrieveProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DatabaseController dbController;
    public RetrieveProductServlet() {
        super();
    }
    
    
    @Override
    public void init() throws ServletException {
        // Initialize the DatabaseController in the init method
        dbController = new DatabaseController(); // Assuming DatabaseController has a constructor
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Fetch all product details from the database
            List<ProductModel> products = dbController.getAllProducts();

            // Verify if products are retrieved
            System.out.println("Number of products retrieved: " + products.size());
            for (ProductModel product : products) {
                System.out.println("Product Name: " + product.getProductName());
                // Print other product attributes as needed
            }

            // Set the list of products as an attribute in the request object
            request.setAttribute("products", products);

            // Forward the request to the products.jsp page
            request.getRequestDispatcher("pages/home.jsp").forward(request, response);
        } catch (Exception e) {
            // Log any exceptions
            e.printStackTrace();
            // Forward to an error page or handle the exception as needed
        }
    }
}
