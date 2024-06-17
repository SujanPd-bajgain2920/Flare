package controller.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.DatabaseController;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    DatabaseController dbController = new DatabaseController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String username = (String) session.getAttribute("user");
        try {
            String cartIdParameter = request.getParameter("cartId");
            if (cartIdParameter != null && !cartIdParameter.isEmpty()) {
                int cartId;
                try {
                    cartId = Integer.parseInt(cartIdParameter);
                } catch (NumberFormatException e) {
                    // Handle invalid cartId parameter
                    System.err.println("Invalid cartId parameter: " + cartIdParameter);
                    response.sendRedirect(request.getContextPath() + "/error.jsp");
                    return;
                }

                int productId;
                try {
                    productId = Integer.parseInt(request.getParameter("productId"));
                } catch (NumberFormatException e) {
                    // Handle invalid productId parameter
                    System.err.println("Invalid productId parameter: " + request.getParameter("productId"));
                    response.sendRedirect(request.getContextPath() + "/error.jsp");
                    return;
                }

                String orderStatus = "pending";

                // Get current date
                Date orderDate = new Date(System.currentTimeMillis());

                // Get user ID based on username
                int userId = dbController.getUserIdByUsername(username);

                // Insert order into the database
                boolean success = dbController.insertOrder(orderDate, orderStatus, cartId, productId, userId);

                if (success) {
                    // Assuming the cart is cleared after the order is placed
                	dbController.updateCartStatus(cartId, "ordered");

                    // Print success message to console
                    System.out.println("Order placed successfully.");

                    // Redirect to a confirmation page
                    response.sendRedirect(request.getContextPath() + "/RetrieveOrderServlet");
                } else {
                    // Handle failure to insert order into the database
                    // request.setAttribute("message", "Failed to place order.");
                    // request.getRequestDispatcher("/pages/message.jsp").forward(request, response);
                    System.out.println("Order failed.");
                }
            } else {
                // Handle the case where "cartId" parameter is missing or empty
                // For example, you can set a default value or return an error response
                System.out.println("Cart ID is missing or empty.");
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        } catch (SQLException | ClassNotFoundException e) {
            // Handle exceptions
            e.printStackTrace(); // Log the exception
            request.setAttribute("message", "Failed to place order: " + e.getMessage());
            request.getRequestDispatcher("/pages/message.jsp").forward(request, response);
        }
    }
}