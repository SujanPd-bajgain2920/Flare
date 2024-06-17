<%@ page import="model.UserModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.OrderModel"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/orderlist.css">
    <header>
        <a class="flare" href="#">
            <img src="${pageContext.request.contextPath}/resources/letter-f.png" alt="">
                    <!-- FLARE -->
        </a>
        <ul class="mid">
           
            <li><a  id="a"  href="${pageContext.request.contextPath}/pages/AddProduct.jsp">Add&nbspProducts</a></li>
             <li><a id="a"  href="${pageContext.request.contextPath}/AdminProductServlet">Product</a></li>
            <li><a class="active" id=""href="${pageContext.request.contextPath}/AdminOrderlistServlet">Order&nbspList</a></li>
        </ul>
        <ul class="right">
            <li><form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
            <button types="submit">Logout</button>
            </form></li>
        </ul>
    </header>
  
</head>
<body>


	<div class="order-list-container">
		<h2>Order List</h2>
		<table class="order-table">
			<thead>
				<tr>
					<th>Product Name</th>
					<th>Order Date</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Total Price</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
			<% 
                    List<OrderModel> cart = (List<OrderModel>) request.getAttribute("orders");
                    if (cart != null && !cart.isEmpty()) {
                        for (OrderModel order : cart) {
                            // Calculate total price for each item
                            int totalAmount = order.getPrice() * order.getQuantity();
                %>
				<tr>
					<td><%=order.getProductName()%></td>
					<td><%=order.getOrderDate()%></td>
					<td><%=order.getQuantity()%></td>
					<td><%=order.getPrice()%></td>
					<td><%=totalAmount%></td>
					<td><select class="status-select"
						id="status<%=order.getOrderId()%>">
							<option value="waiting"
								<%=order.getStatus().equals("waiting") ? "selected" : ""%>>Waiting</option>
							<option value="Delivered"
								<%=order.getStatus().equals("Delivered") ? "selected" : ""%>>Delivered</option>
					</select></td>
					<td>
						<button class="btn"
							onclick="window.location.href='${pageContext.request.contextPath}/StatusServlet?orderId=<%=order.getOrderId()%>&orderStatus=' + getSelectedStatus('<%=order.getOrderId()%>')">Update</button>
					</td>
				</tr>
				<%
				}
				%>
				<%
				} else {
				%>
				<tr>
					<td colspan="7">No orders available.</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>

	<script>
		function getSelectedStatus(orderId) {
			var selectElement = document.getElementById('status' + orderId);
			return selectElement.value;
		}
	</script>
</body>
</html>
