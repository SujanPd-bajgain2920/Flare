<%@ page import="model.CartModel" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/cart.css">
</head>
<body>
    <div class="container">
        <h1>Shopping Cart</h1>
        <table>
            <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<CartModel> cart = (List<CartModel>) request.getAttribute("cart");
                    if (cart != null && !cart.isEmpty()) {
                        for (CartModel cartItem : cart) {
                            // Calculate total price for each item
                            int totalAmount = cartItem.getProductPrice() * cartItem.getQuantity();
                %>
                <tr>
                    <td><%= cartItem.getProductName() %></td>
                    <td class="price">$<%= cartItem.getProductPrice() %></td>
                    <td class="quantity"><%= cartItem.getQuantity() %></td> <!-- Display quantity directly -->
                    <td class="total">$<%= totalAmount %></td> <!-- Display calculated total amount -->
                    <td>
                        <form action="${pageContext.request.contextPath}/DeleteCartServlet" method="post">
                            <input type="hidden" name="deleteId" value="<%= cartItem.getCartId() %>">
                            <button type="submit">Remove</button>
                        </form>
                        <form action="${pageContext.request.contextPath}/OrderServlet" method="post">
                            <input type="hidden" name="cartId" value="<%= cartItem.getCartId() %>">
                            <input type="hidden" name="productId" value="<%= cartItem.getProductId() %>">
                            <button type="submit">Order</button>
                        </form>
                    </td>
                </tr>
                <% 
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5">No items in the cart</td>
                </tr>
                <% } %>
            </tbody>
        </table>
        
        <%-- Add continue shopping button here --%>
        <form action="${pageContext.request.contextPath}/RetrieveOrderServlet">
        <button class="btn">View Orders</button>
        </form>
    </div>

    <%-- Additional JavaScript or CSS can be included here --%>
</body>
</html>
