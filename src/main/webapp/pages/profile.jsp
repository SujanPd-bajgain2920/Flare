<%@ page import="model.UserModel" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/profile.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/header.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<h1>My Profile</h1>

<div class="profile-info">
    <% 
    UserModel user = (UserModel) request.getAttribute("user");
    if (user != null) {
    %> 
    <p>Name: <%= user.getName()%></p>
    <p>User Name: <%= user.getUserName()%></p>
    <p>Email: <%= user.getEmail()%></p>
    <p>Phone: <%= user.getPhone()%></p>
    
    <button id="seeOrdersBtn">See Orders</button>
    
     <button type="button" class="order" onclick="populateUpdateModal('<%= user.getUserId() %>', '<%= user.getName() %>', '<%= user.getUserName() %>', '<%= user.getEmail() %>', '<%= user.getPhone() %>')">
    			Edit
				</button>
<div id="updateFormContainer" style="display: none;">			
	<form id="updateProfile" action="${pageContext.request.contextPath}/UpdateProfileServlet" method="POST">
	<input type="hidden" id="updateId" name="updateId">
	
	    <label for="name">Name:</label><br>
        <input type="text" id="name" name="name" required><br><br>
        
        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email" required><br><br>
        
        <label for="phone">Phone:</label><br>
        <input type="tel" id="phone" name="phone" required><br><br>
        
        <button type="button" class="form-button" onclick="updateProfile()">Save Changes</button>
    </form>
 </div>
    
    <% 
    } else {
    %>
    <p>No Profile found</p>
    <% 
    }
    %>
</div>

<div id="orderListSection" style="display: none;">
    <!-- Order list will be displayed here -->
</div>

<script>

function populateUpdateModal(userId, name, userName, email, phone) {
	// Populate form fields with user details
    document.getElementById('updateId').value = userId;
    document.getElementById('name').value = name; // Assigning name to name input
    document.getElementById('email').value = email;
    document.getElementById('phone').value = phone;
    // Show the update product form container
    document.getElementById('updateFormContainer').style.display = 'block';

// Show the modal
$('#updateModal').modal('show');
}
function updateProfile() {
    document.getElementById('updateProfile').submit();
}

    $(document).ready(function() {
        $("#seeOrdersBtn").click(function() {
            $.ajax({
                type: "GET",
                url: "${pageContext.request.contextPath}/RetrieveOrderServlet",
                success: function(data) {
                    // Update the order list section with the fetched data
                    $("#orderListSection").html(data);
                    $("#orderListSection").show(); // Show the order list section
                },
                error: function() {
                    alert("Failed to fetch order list.");
                }
            });
        });
    });
</script>

</body>
</html>
