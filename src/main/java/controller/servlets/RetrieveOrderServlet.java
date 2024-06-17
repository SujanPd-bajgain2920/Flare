package controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseController;
import model.OrderModel;

/**
 * Servlet implementation class RetrieveOrderServlet
 */
@WebServlet("/RetrieveOrderServlet")
public class RetrieveOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseController dbController = new DatabaseController();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Get the username from the session
            String username = (String) request.getSession().getAttribute("user");
            // Fetch user ID using username
            int userId = dbController.getUserIdByUsername(username);

            if (userId != -1) { // Ensure that user ID is valid (-1 indicates user not found)
                // Fetch order details for the specific user from the database
                List<OrderModel> orders = dbController.getOrdersByUserId(userId);

                // Set the attribute for order list
                request.setAttribute("orders", orders);

                // Forward the request to the appropriate JSP page
                request.getRequestDispatcher("pages/userOrder.jsp").forward(request, response);
            } else {
                // Handle case where user is not found
                // Redirect or display an error message
            }
        } catch (Exception e) {
            // Log any exceptions
            e.printStackTrace();
            // Forward to an error page or handle the exception as needed
        }
    }
}