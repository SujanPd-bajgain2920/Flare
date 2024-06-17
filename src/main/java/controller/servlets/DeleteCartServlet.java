package controller.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.DatabaseController;
import util.StringUtils;

/**
 * Servlet implementation class DeleteCartServlet
 */
@WebServlet("/DeleteCartServlet")
public class DeleteCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseController dbController = new DatabaseController();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCartServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String deleteId = request.getParameter("deleteId");

		if (deleteId != null && !deleteId.isEmpty()) {
			int productId = Integer.parseInt(deleteId);
			boolean deleted = dbController.deletecart(productId);

			if (deleted) {
				System.out.println("Item successfully removed from the cart.");
			} else {
				System.out.println("Failed to remove item from the cart.");
			}
		}

		response.sendRedirect(request.getContextPath() + "/CartServlet");

	}

}
