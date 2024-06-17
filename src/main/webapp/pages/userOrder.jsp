<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.OrderModel"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f9f9f9;
        margin: 0;
        padding: 0;
    }
    .table {
        width: 80%;
        margin: 50px auto;
        border-collapse: collapse;
    }
    h2 {
        text-align: center;
        margin-bottom: 20px;
        color: #333;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        box-shadow: 0 2px 3px rgba(0,0,0,0.1);
    }
    th, td {
        padding: 10px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
        color: #333;
        border-bottom: 2px solid #ddd;
    }
    td {
        background-color: #fff;
        border-bottom: 1px solid #ddd;
    }
    tr:nth-child(even) td {
        background-color: #f9f9f9;
    }
    tr:hover td {
        background-color: #f1f1f1;
    }
    td[colspan="6"] {
        text-align: center;
        color: #777;
        padding: 20px;
    }
</style>
</head>
<body>
<div class="table">
		<h2> Orders History</h2>
		<table>
			<thead>
				<tr>
					<th>Product Name</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Total Price</th>
					<th>Order Date</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<%
				// Iterate through the list of orders and populate the table rows
				List<OrderModel> orders = (List<OrderModel>) request.getAttribute("orders");
				if (orders != null && !orders.isEmpty()) {
					for (OrderModel order : orders) {
						// Calculate total price for each item
                        int totalAmount = order.getPrice() * order.getQuantity();
				%>
				<tr>
					<td><%=order.getProductName()%></td>
					<td><%=order.getQuantity()%></td>
					<td><%=order.getPrice()%></td>
					<td><%=totalAmount%></td>
					<td><%=order.getOrderDate()%></td>
					<td><%=order.getStatus()%></td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="6">No orders available.</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>