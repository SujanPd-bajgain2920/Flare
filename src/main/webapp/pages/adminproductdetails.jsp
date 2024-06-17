<%@ page import="model.ProductModel" %>
<%@ page import="java.util.List" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/adminproductdetails.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/AddProduct.css">
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
                <button type="button" class="order" onclick="populateUpdateModal('<%= product.getProductId() %>', '<%= product.getProductName() %>', '<%= product.getProductPrice() %>', '<%= product.getProductStock() %>', '<%= product.getBrandName() %>', '<%= product.getAutoFocus() %>', '<%= product.getProductFlash() %>', '<%= product.getProductType() %>', '<%= product.getProductLens() %>', '<%= product.getSensorSize() %>', '<%= product.getMovieFormat() %>', '<%= product.getImageUrlFromPart() %>')">
    				<i class="fas fa-edit"></i> Edit
				</button>
				<form id="deleteForm-<%= product.getProductName() %>" method="post" action="${pageContext.request.contextPath}/ModifyProductServlet">
				<input type="hidden" id="deleteId" name="deleteId" value="<%= product.getProductId() %>">
				<button class="cart" role="button" onclick="return confirmDelete('<%= product.getProductName() %>')">
                     <i class="fas fa-trash-alt"></i> Delete
                </button>
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


<!-- Container for the update product form -->
<div id="updateFormContainer" style="display: none;">
<!-- Update Product Form -->
<form id="updateProduct" action="${pageContext.request.contextPath}/ModifyProductServlet" method="post" enctype="multipart/form-data">
			
                <div class="left-column">
                <input type="hidden" id="updateId" name="updateId">
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
                        <button type="button" class="form-button" onclick="hideUpdateForm()">Cancel</button>
    					<button type="button" class="form-button" onclick="updateProduct()">Save Changes</button>
                    </div>
                </form>
                </div>
                
<script>

function confirmDelete(productName) {
    console.log("Deleting product: " + productName); // Print message to console
    if(confirm("Are you sure you want to delete this product: " + productName + "?")){
        document.getElementById("deleteForm-" + productName).submit();
    } else {
        return false;
    }
}


	 function populateUpdateModal(productid, productName, productPrice, productStock, productBrand, autoFocus, builtInFlash, cameraType, productLens, sensorSize, movieFormat, imageUrl) {
	        // Populate form fields with product details
	        document.getElementById('updateId').value = productid;
	        document.getElementById('productName').value = productName;
	        document.getElementById('productBrand').value = productBrand;
	        document.getElementById('productPrice').value = productPrice;
	        document.getElementById('productStock').value = productStock;
	        document.getElementById('autoFocus').value = autoFocus;
	        document.getElementById('builtinFlash').value = builtInFlash;
	        document.getElementById('cameraType').value = cameraType;
	        document.getElementById('productLens').value = productLens;
	        document.getElementById('sensorSize').value = sensorSize;
	        document.getElementById('movieFormat').value = movieFormat;
	        document.getElementById('productImage').src = "${pageContext.request.contextPath}/resources/images/" + imageUrl;

	        // Show the update product form container
	        document.getElementById('updateFormContainer').style.display = 'block';
	   
	    // Show the modal
	    $('#updateModal').modal('show');
	}
	 function hideUpdateForm() {
	        // Hide the update product form container
	        document.getElementById('updateFormContainer').style.display = 'none';
	    }

	


    function updateProduct() {
        document.getElementById('updateProduct').submit();
    }
</script>
                