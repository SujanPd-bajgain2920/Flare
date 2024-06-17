<%@ page import="model.ProductModel" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/header.css">
   
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/home.css">
    
    <title>Flare</title>
</head>

<body>
   <jsp:include page="header.jsp"></jsp:include>
    <section>
        <div class="home">
            <img src="${pageContext.request.contextPath}/resources/BackgroundHome.jpg" alt="">
            <h1 class="main_tag">Flare</h1>
            <h1 class="tag">Where Every Frame Ignites <span>Inspiration</span></h1>
            <button>EXPLORE</button>
        </div>
    </section>
    <div class="page">        
        <div class="description">
            <svg xmlns="http://www.w3.org/2000/svg"
                viewBox="0 0 384 512"><!--! Font Awesome Pro 6.2.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2022 Fonticons, Inc. -->
                <path
                    d="M32 0C14.3 0 0 14.3 0 32S14.3 64 32 64V75c0 42.4 16.9 83.1 46.9 113.1L146.7 256 78.9 323.9C48.9 353.9 32 394.6 32 437v11c-17.7 0-32 14.3-32 32s14.3 32 32 32H64 320h32c17.7 0 32-14.3 32-32s-14.3-32-32-32V437c0-42.4-16.9-83.1-46.9-113.1L237.3 256l67.9-67.9c30-30 46.9-70.7 46.9-113.1V64c17.7 0 32-14.3 32-32s-14.3-32-32-32H320 64 32zM96 75V64H288V75c0 19-5.6 37.4-16 53H112c-10.3-15.6-16-34-16-53zm16 309c3.5-5.3 7.6-10.3 12.1-14.9L192 301.3l67.9 67.9c4.6 4.6 8.6 9.6 12.2 14.9H112z" />
            </svg>
            <h1>+2<br><span>Years</span></h1>
            <div>
                <svg xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 512 512"><!--! Font Awesome Pro 6.2.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2022 Fonticons, Inc. -->
                    <path
                        d="M512 64c0 113.6-84.6 207.5-194.2 222c-7.1-53.4-30.6-101.6-65.3-139.3C290.8 78.3 364 32 448 32h32c17.7 0 32 14.3 32 32zM0 128c0-17.7 14.3-32 32-32H64c123.7 0 224 100.3 224 224v32 96c0 17.7-14.3 32-32 32s-32-14.3-32-32V352C100.3 352 0 251.7 0 128z" />
                </svg>
            </div>
            <h1>+15<br><span>Variety</span></h1>
            <div>
                <svg xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 640 512"><!--! Font Awesome Pro 6.2.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2022 Fonticons, Inc. -->
                    <path
                        d="M144 160c-44.2 0-80-35.8-80-80S99.8 0 144 0s80 35.8 80 80s-35.8 80-80 80zm368 0c-44.2 0-80-35.8-80-80s35.8-80 80-80s80 35.8 80 80s-35.8 80-80 80zM0 298.7C0 239.8 47.8 192 106.7 192h42.7c15.9 0 31 3.5 44.6 9.7c-1.3 7.2-1.9 14.7-1.9 22.3c0 38.2 16.8 72.5 43.3 96c-.2 0-.4 0-.7 0H21.3C9.6 320 0 310.4 0 298.7zM405.3 320c-.2 0-.4 0-.7 0c26.6-23.5 43.3-57.8 43.3-96c0-7.6-.7-15-1.9-22.3c13.6-6.3 28.7-9.7 44.6-9.7h42.7C592.2 192 640 239.8 640 298.7c0 11.8-9.6 21.3-21.3 21.3H405.3zM416 224c0 53-43 96-96 96s-96-43-96-96s43-96 96-96s96 43 96 96zM128 485.3C128 411.7 187.7 352 261.3 352H378.7C452.3 352 512 411.7 512 485.3c0 14.7-11.9 26.7-26.7 26.7H154.7c-14.7 0-26.7-11.9-26.7-26.7z" />
                </svg>
            </div>
            <h1>+100<br><span>Clients</span></h1>
            <div class="hand">
                <svg xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 576 512"><!--! Font Awesome Pro 6.2.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2022 Fonticons, Inc. -->
                    <path
                        d="M559.7 392.2c17.8-13.1 21.6-38.1 8.5-55.9s-38.1-21.6-55.9-8.5L392.6 416H272c-8.8 0-16-7.2-16-16s7.2-16 16-16h16 64c17.7 0 32-14.3 32-32s-14.3-32-32-32H288 272 193.7c-29.1 0-57.3 9.9-80 28L68.8 384H32c-17.7 0-32 14.3-32 32v64c0 17.7 14.3 32 32 32H192 352.5c29 0 57.3-9.3 80.7-26.5l126.6-93.3zm-367-8.2c.3 0 .6 0 .9 0c0 0 0 0 0 0c-.3 0-.6 0-.9 0z" />
                </svg>
            </div>
            <h1 class="last">100% <br><span>Quality</span> </h1>
        </div>
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
        <p class="price">$:<%= product.getProductPrice() %></p>
        <div class="product_buttons">
            <button onclick="location.href='${pageContext.request.contextPath}/ProductDetailsServlet?productName=<%= product.getProductName() %>'" class="details">See Details</button>
            <form action="${pageContext.request.contextPath }/AddCartServlet?productId=<%=product.getProductId()%>">
                <input type="hidden" name="productId" value="<%=product.getProductId()%>">
                <button type="submit" class="cart">Add To Cart</button>
            </form>
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
</body>
</html>
