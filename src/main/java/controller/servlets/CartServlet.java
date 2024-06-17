package controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.DatabaseController;
import model.CartModel;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseController dbController = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    HttpSession session = request.getSession(false);
	    if (session != null && session.getAttribute("user") != null) {
	        String user = (String) session.getAttribute("user");

	        List<CartModel> carts = dbController.getCartItemsByUsername(user);
	        
	        if (carts != null && !carts.isEmpty()) {
	            System.out.println("Cart items retrieved successfully: " + carts.size() + " items");
	        } else {
	            System.out.println("No cart items found for the user");
	        }
	        
	        request.setAttribute("cart", carts);

	        request.getRequestDispatcher("/pages/cart.jsp").forward(request, response);
	    } else {
	        System.out.println("Failed to retrieve user session or user not logged in");
	        request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
	    }
	}
}