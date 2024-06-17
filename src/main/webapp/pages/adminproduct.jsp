
<%@ page import="model.ProductModel" %>
<%@ page import="java.util.List" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/adminproduct.css">
<header>
        <a class="flare" href="#">
            <img src="${pageContext.request.contextPath}/resources/letter-f.png" alt="">
                    <!-- FLARE -->
        </a>
        <ul class="mid">
           
            <li><a  id="a"  href="${pageContext.request.contextPath}/pages/AddProduct.jsp">Add&nbspProducts</a></li>
             <li><a class="active" id="" href="${pageContext.request.contextPath}/AdminProductServlet">Product</a></li>
            <li><a id="a" href="${pageContext.request.contextPath}/AdminOrderlistServlet">Order&nbspList</a></li>
        </ul>
        <ul class="right">
            <li><form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
            <button types="submit">Logout</button>
            </form></li>
        </ul>
    </header>
    <div class="products">
        
            <h1>Our Products</h1>
            
           <div class="cameraflex">
    <% 
    List<ProductModel> products = (List<ProductModel>) request.getAttribute("products");
    if (products != null && !products.isEmpty()) {
        for (ProductModel product : products) {
    %> 
    <div class="camera">
        <img src="${pageContext.request.contextPath}/resources/images/<%= product.getImageUrlFromPart() %>" alt="<%= product.getProductName() %>">
        <p class="name"><%= product.getProductName() %></p>
        <p class="price">$<%= product.getProductPrice() %></p>
        <div class="product_buttons">
            <button onclick="location.href='${pageContext.request.contextPath}/AdminProductDetailsServlet?productName=<%= product.getProductName() %>'" class="details">See Details</button>
            
        </div>
    </div>
    <% 
        }
    } else {
    %>
    <p>No products found</p>
    <% 
    }
    %>
</div>
        </div>
</div>