package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DatabaseController;
import model.UserModel;
import util.StringUtils;

@WebServlet(asyncSupported = true, urlPatterns = StringUtils.FEEDBACK_SERVLET)
public class FeedbackSubmissionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseController dbController;

    public void init() throws ServletException {
        super.init();
        // Initialize DatabaseController instance
        dbController = new DatabaseController();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve feedback data from the request
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String description = request.getParameter("description");

            // Validate input
            if (name == null || email == null || description == null || name.isEmpty() || email.isEmpty()
                    || description.isEmpty()) {
                throw new IllegalArgumentException("Name, email, and description are required.");
            }

            // Create UserModel instance
            UserModel feedbacksubmitModel = new UserModel();
            feedbacksubmitModel.setFeedbackSubName(name);
            feedbacksubmitModel.setFeedbackSubEmail(email);
            feedbacksubmitModel.setFeedbackSubDescription(description);

            // Add feedback to the database
            int result = dbController.addFeedback(feedbacksubmitModel);

            // Redirect based on the result
            if (result > 0) {
                response.sendRedirect(request.getContextPath()+ StringUtils.RETRIEVE_PRODUCT_SERVLET);
                
            } else {
                throw new ServletException("Failed to add feedback to the database.");
            }
        } catch (Exception e) {
            // Error handling
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
        }
    }
}
