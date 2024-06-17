package controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void destroy() {
        // Cleanup resources if needed
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        // Check if the requested resource is a static resource
        if (uri.endsWith(".css") || uri.endsWith(".jpg") || uri.endsWith(".jpeg") || uri.endsWith(".png")) {
            chain.doFilter(request, response);
            return;
        }

        // Allow access to login and registration pages
        if (uri.endsWith("login.jsp") || uri.endsWith("LoginServlet") || uri.endsWith("Register.jsp") || uri.endsWith("RegisterServlet") || uri.endsWith("RetrieveProductServlet")) {
            chain.doFilter(request, response);
            return;
        }
        
        if (uri.endsWith("aboutus.jsp") || uri.endsWith("feedbackSubmit") || uri.endsWith("SubmitFeedbackServlet"))
        {
        	System.out.print(uri);
        	chain.doFilter(req, res);
        	return;
        } 
        
        // Allow access to about us page without login
        if (uri.endsWith("aboutus.jsp")) {
            chain.doFilter(request, response);
            return;
        }

     // Allow access to home page without login when coming from about us page
        if (uri.endsWith("home.jsp") && req.getHeader("referer").endsWith("aboutus.jsp")) {
            req.getRequestDispatcher("/pages/home.jsp").forward(request, response);
            return;
        }

        
        // If hitting the main folder, retrieve products and forward to home page
        if (uri.equals(req.getContextPath() + "/")) {
            // Retrieve products here (assuming RetrieveProductServlet is properly implemented)
            req.getRequestDispatcher("/RetrieveProductServlet").include(request, response);
            req.getRequestDispatcher("/pages/home.jsp").forward(request, response);
            return;
        }

        // Retrieve session
        HttpSession session = req.getSession(false);
        if (session == null) {
            res.sendRedirect(req.getContextPath() + "/pages/login.jsp");
            return;
        }

        // Check user authentication
        boolean isLoggedIn = session.getAttribute("user") != null;
        String userRole = (String) session.getAttribute("role");

        if (!isLoggedIn) {
            res.sendRedirect(req.getContextPath() + "/pages/login.jsp");
            return;
        }

        // Allow access to admin pages only if user is admin
        if ("admin".equals(userRole) && (uri.endsWith("AddProduct.jsp") || uri.endsWith("AddProductServlet") || uri.endsWith("product.jsp"))) {
            chain.doFilter(request, response);
            return;
        }

        // Allow access to user pages for both admin and regular users
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // Initialization logic if needed
    }
}
