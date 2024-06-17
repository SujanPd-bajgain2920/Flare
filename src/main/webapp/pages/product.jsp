
<%@ page import="model.ProductModel" %>
<%@ page import="java.util.List" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/product.css">
    <div class="products">
        
            <h1>Our Products</h1>
            
            <div class="cameraflex">
             
                <div class="camera">
                 <% 
                List<ProductModel> products = (List<ProductModel>) request.getAttribute("products");
                if (products != null && !products.isEmpty()) {
                    for (ProductModel product : products) {
            %> 
            		 <img src="${pageContext.request.contextPath}/resources/images/<%= product.getImageUrlFromPart() %>" alt="<%= product.getProductName() %>">
                    <p class="name"><%= product.getProductName() %></p>
                    <p class="price"><%= product.getPrice() %></p>
                    <div class="product_buttons">
                        <button class="details">See&nbspDetails</button>
                        <button class="cart">Add&nbspTo&nbspCart</button>
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
</div>