<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String cookieUsername = null;
    Cookie[] cookies = request.getCookies();
    if(cookies!=null) {
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals("user")) cookieUsername = cookie.getValue();
        }
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/header.css">
    <title>Nav Bar</title>
</head>
<body>
    <header>
        <a class="flare" href="#">
            <img src="${pageContext.request.contextPath}/resources/letter-f.png" alt="..">
            <!-- FLARE -->
        </a>
        <ul class="mid">
            <li><a  id="a"  href="${pageContext.request.contextPath}/RetrieveProductServlet">Home</a></li>
            <li><a id="a" href="${pageContext.request.contextPath}/pages/aboutus.jsp">About&nbspUs</a></li>
            <li><a id="a" href="${pageContext.request.contextPath}/ProfileServlet">Profile</a></li>
        </ul>
        <ul class="rightnav">
            <li>
                <form action="/search" method="get">
                    <input type="text" placeholder="Search..." name="search">
                    <button type="submit">Search</button>
                </form>
            </li>
        </ul>
        <ul class="right">
            <li>
                <form action="${pageContext.request.contextPath}/CartServlet">
                    <button type="submit" id="cart" class="cart-button">
                        My Cart
                    </button>
                </form>
            </li>
            <% if (cookieUsername != null){ %>
                <li>
                    <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
                        <button types="submit">Logout</button>
                    </form>
                </li>
            <% } else { %>
                <li><a href="${pageContext.request.contextPath}/pages/login.jsp">Login</a></li>
            <% } %>
        </ul>
    </header>
</body>
</html>
