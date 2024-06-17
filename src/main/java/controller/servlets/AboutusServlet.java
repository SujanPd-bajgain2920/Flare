package controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/aboutus")
public class AboutusServlet extends HttpServlet {
   
	
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
    	
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String description=request.getParameter("description");
        
        
        System.out.println("Name: " + name);
        System.out.println("Email:" + email);
        System.out.println("Description:" + description);
        
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Thank You for your feedback</title></head><body>");
        out.println("<h2>Thank You for your feedback, " + name +"</h2>");
        out.println("<p>We are kindly obliged to hear from you.</p>");
        out.println("</body></html>");
        
        out.close();
        		

        // Retrieve form data
     

    }
}
       
