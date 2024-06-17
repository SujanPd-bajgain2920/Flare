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
import model.ProductModel;
import model.UserModel;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private DatabaseController dbController;
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    

    @Override
    public void init() throws ServletException {
        // Initialize the DatabaseController in the init method
        dbController = new DatabaseController(); // Assuming DatabaseController has a constructor
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Get the username of the currently logged-in user from the session
        String User = (String) session.getAttribute("user");

        try {
            // Call the getUserDetails method from the DatabaseController to retrieve user details
            DatabaseController dbController = new DatabaseController();
            UserModel user = dbController.getUserDetails(User);

            if (user != null) {
                // If user details are retrieved successfully, set them as an attribute in the request object
                request.setAttribute("user", user);
                // Forward the request to the appropriate JSP page
                request.getRequestDispatcher("pages/profile.jsp").forward(request, response);
            } else {
                // If no user found with the logged-in username, redirect to an error page or display an error message
                response.sendRedirect("error.jsp"); // Example: Redirect to error.jsp
            }
        } catch (Exception e) {
            // Log any exceptions
            e.printStackTrace();
            // Forward to an error page or handle the exception as needed
        }
    }
}