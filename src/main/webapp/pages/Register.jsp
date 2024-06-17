
<%@page import="model.UserModel"%>
<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/Register.css">
</head>
<body>
<div class="img">
        <img src="${pageContext.request.contextPath}/resources/register.jpg" alt="">
    </div>

    
    <div class="container">
        <h2>Register</h2>
        <form action="${pageContext.request.contextPath}/RegisterServlet" method="post">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" placeholder="Name" required>
            </div>
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" placeholder="Username" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" placeholder="Email" required>
            </div>
            <div class="form-group">
                <label for="gender">Gender:</label>
                <select id="gender" name="gender" required>
                    <option value="" disabled selected>Select your gender</option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option value="other">Other</option>
                </select>
            </div>
            <div class="form-group">
                <label for="phone">Phone Number:</label>
                <input type="tel" id="phone" name="phone" placeholder="Phone Number" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" placeholder="Password" required>
            </div>
             <div class="form-group">
                <label for="confirm_password">Confirm Password:</label>
                <input type="password" id="confirm_password" name="confirm_password" placeholder="Confirm Password" required>
            </div>
            <input type="submit" value="Register">
        </form>
        <p>Already have an account? <a href="../pages/login.jsp" class="login-link">Login</a></p>
         <%
        String errorMessage = (String) request.getAttribute(StringUtils.ERROR_MESSAGE);
    
        
        if (errorMessage !=null && !errorMessage.isEmpty()) {
    %>
        <div class="alert alert-danger" role="alert">
            <%= errorMessage %>
        </div>
        <% 
        }
        %>
    </div>
</body>
</html>