<%@page import="model.ProductModel"%>
<%@page import="util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add Product Page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/AddProduct.css">
</head>
<body>
 <header>
        <a class="flare" href="#">
            <img src="/letter-f.png" alt="">
                    <!-- FLARE -->
        </a>
        <ul class="mid">
            <li><a id="" href="${pageContext.request.contextPath}/AdminProductServlet">Product</a></li>
            <li><a class="active" href="">Add&nbspProducts</a></li>
            <li><a id="a" href="${pageContext.request.contextPath}/AdminOrderlistServlet">Order&nbspList</a></li>
        </ul>
        <ul class="right">
            <li><form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
            <button types="submit">Logout</button>
            </form></li>
        </ul>
    </header>
    <div class="flex">
        <div class="img">
            <img src="/46ec0ff17f3e86e31deb1961189c1a5a.jpg" alt="">
        </div>
        <div class="container">
            <h1>Add&nbspProduct</h1>
            <form action="${pageContext.request.contextPath}/AddProductServlet" method="post" enctype="multipart/form-data">
                <div class="left-column">
                    <div class="form-group">
                        <label for="product-name" class="form-label">Product Name:</label>
                        <input type="text" id="productName" name="productName" class="form-input" required>
                    </div>
                    <div class="form-group">
                        <label for="product-brand" class="form-label">Brand Name:</label>
                        <input type="text" id="productBrand" name="productBrand" class="form-input" required>
                    </div>
                        <div class="form-group">
                            <label for="product-price" class="form-label">Price:</label>
                            <input type="text" id="productPrice" name="productPrice" class="form-input" required>
                        </div>
                        <div class="form-group">
                            <label for="product-stock" class="form-label">Stock:</label>
                            <input type="number" id="productStock" name="productStock" class="form-input" required>
                        </div>
                        <div class="form-group">
                            <label for="auto-focus" class="form-label">Auto Focus(AF):</label>
                            <select id="autoFocus" name="autoFocus" class="form-input" required>
                                <option value="yes">Yes</option>
                                <option value="no">No</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="built-in-flash" class="form-label">Built-In Flash:</label>
                            <select id="builtinFlash" name="builtinFlash" class="form-input" required>
                                <option value="available">Available</option>
                                <option value="not-available">Not Available</option>
                            </select>
                        </div>
                    </div>
                    <div class="right-column">
    <div class="form-group">
        <label for="camera-type" class="form-label">Camera Type:</label>
        <select id="cameraType" name="cameraType" class="form-input" required>
            <option value="dslr">DSLR</option>
            <option value="mirrorless">Mirrorless</option>
        </select>
    </div>
    <div class="form-group">
        <label for="product-lens" class="form-label">Lens:</label>
        <input type="text" id="productLens" name="productLens" class="form-input" required>
    </div>
    <div class="form-group">
        <label for="sensor-size" class="form-label">Sensor Size:</label>
        <input type="text" id="sensorSize" name="sensorSize" class="form-input" required>
    </div>
    <div class="form-group">
        <label for="movie-format" class="form-label">Movie Format:</label>
        <input type="text" id="movieFormat" name="movieFormat" class="form-input" required>
    </div>
    <div class="form-group">
        <label for="product-image" class="form-label">Image:</label>
        <input type="file" id="productImage" name="productImage" accept="image/*" class="form-input" required>
    </div>
    <button type="submit" class="form-button">Add Product</button>
</div>

                </form>
                
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
    </div>
    </body>
</html>