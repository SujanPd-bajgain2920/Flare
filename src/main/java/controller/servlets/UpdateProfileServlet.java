package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.DatabaseController;
import model.UserModel;
import util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class UpdateProfileServlet
 */
@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    DatabaseController dbController = new DatabaseController();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String updateId = request.getParameter("updateId");

        System.out.println(updateId);

        if (updateId != null && !updateId.isEmpty()) {
            doPut(request, response);
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the request
        String name = request.getParameter(StringUtils.NAME);
        String email = request.getParameter(StringUtils.EMAIL);
        String phone = request.getParameter(StringUtils.PHONE_NUMBER);
        
        HttpSession session = request.getSession(false); // Get the session without creating a new one
        if (session != null) {
            String loggedInUserName = (String) session.getAttribute("user"); // Retrieve userName from the session

            // Retrieve userId using the method from the dbController
            int userId = dbController.getUserIdByUsername(loggedInUserName); 

            // Create a UserModel object with the updated information
            UserModel updateProfile = new UserModel();
            updateProfile.setUserId(userId); // Set userId
            updateProfile.setName(name);
            updateProfile.setEmail(email);
            updateProfile.setPhone(phone);
            
            // Update profile information in the database
            int result = dbController.updateProfile(updateProfile);
            if (result == 1) {
                // Profile update successful
                System.out.println("Profile update successful");
                request.setAttribute(StringUtils.SUCCESS_MESSAGE, "Profile update successful");
                response.sendRedirect(request.getContextPath() + StringUtils.PROFILE_SERVLET);
            } else {
                // Profile update failed
                System.out.println("Profile update failed");
                request.setAttribute(StringUtils.ERROR_MESSAGE, "Profile update failed");
                response.sendRedirect(request.getContextPath() + StringUtils.PROFILE_SERVLET);
            }
        } else {
            // Session expired or user not logged in
            System.out.println("Session expired or user not logged in");
            response.sendRedirect(request.getContextPath() + StringUtils.LOGIN_SERVLET); // Redirect to login page
        }
    }
}