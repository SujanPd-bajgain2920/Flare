<%@ page import="model.ProductModel" %>
<%@ page import="java.util.List" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/productdetails.css">
<div class="products">
            <% 
            ProductModel product = (ProductModel) request.getAttribute("product");
            if (product != null) {
            %> 
    <h1>About <%= product.getProductName() %></h1>
    <div class="cameraflex">
        <div class="camera">
                <img src="${pageContext.request.contextPath}/resources/images/<%= product.getImageUrlFromPart() %>" alt="<%= product.getProductName() %>">
                <div class="details">
				<div class="detailflex">
                <div class="left"> 
                <p id="detail" class="name">Name: <%= product.getProductName() %></p>
                <p id="detail"  class="price">Price: $<%= product.getProductPrice() %></p>
                <p id="detail"  class="Quantity">Quantity Available: <%= product.getProductStock() %></p>
                <p id="detail"  class="Brand">Brand Name: <%= product.getBrandName() %></p>
                <p id="detail"  class="AF">AutoFocus(AF): <%= product.getAutoFocus() %></p>
                </div>
				<div class="right">
				<p id="detail"  class="Flash">Flash: <%= product.getProductFlash() %></p>
                <p id="detail"  class="type">Camera Type: <%= product.getProductType() %></p>
                <p id="detail"  class="Lens">Lens: <%= product.getProductLens() %></p>
                <p id="detail"  class="Sensor">Sensor Size: <%= product.getSensorSize() %></p>
                <p id="detail"  class="price">Movie Format: <%= product.getMovieFormat() %></p>
				</div>
				</div>
                <div class="product_buttons">
                <form action="${pageContext.request.contextPath }/AddCartServlet?productId=<%=product.getProductId()%>">
                <input type="hidden" name="productId" value="<%=product.getProductId()%>">
                <button type="submit" class="cart">Add To Cart</button>
            </form>
                </div>
                </div>
            <% 
            } else {
            %>
                <p>No products found</p>
            <% 
            }
            %>
        </div>
    </div>
</div>