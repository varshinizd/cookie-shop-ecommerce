<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, model.Product, model.User" %>
<%@ page session="true" %>
<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    User user = (User) session.getAttribute("user");
    String currentUser = (user != null) ? user.getUsername() : null;%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home - Product Listings</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/home.css?v=4">
</head>
<body>

	<div class="top-bar">
	    <h1>Welcome, <%= (currentUser != null) ? currentUser : "Guest" %>!</h1>
	
	    <input type="text" id="searchBox" placeholder="Search products..." />
	
	    <% if ("admin".equalsIgnoreCase(currentUser)) { %>
	        <button class="add-btn" onclick="openOverlay()">+ Add Product</button>
	    <% } %>
	</div>

    <div class="product-grid">
        <% if (products != null && !products.isEmpty()) {
            for (Product p : products) { %>
                <div class="product-card">
                	<img src="image?name=<%= p.getImagePath() %>" alt="Product Image" 
     				style="width: 200px; height: auto; object-fit: cover;">

                    <h3><%= p.getName() %></h3>
                    <p><%= p.getDescription() %></p>
                    <p><strong>₹ <%= p.getCost() %></strong></p>
                </div>
        <%  }
           } else { %>
            <p class="no-products">No products to display.</p>
        <% } %>
    </div>

    <%-- Overlay Form (Admin Only) --%>
    <% if ("admin".equalsIgnoreCase(currentUser)) { %>
    <div class="overlay" id="overlayForm">
        <div class="form-box">
            <span class="close-btn" onclick="closeOverlay()">×</span>
            <h2>Add New Product</h2>
            <form action="addProduct" method="post" enctype="multipart/form-data">

                <!-- Custom styled file input -->
                <label for="productImage" class="custom-file-label">Choose Product Image</label>
                <input type="file" id="productImage" name="image" accept="image/*" onchange="previewImage(event)" required>
                <img id="imagePreview" src="#" alt="Image Preview" style="display: none;" />

                <!-- Other form fields -->
                <label for="name">Product Name:</label>
                <input type="text" name="name" required>

                <label for="description">Description:</label>
                <textarea name="description" rows="3" required></textarea>

                <label for="cost">Cost (₹):</label>
                <input type="number" name="cost" step="0.1" required>

                <button type="submit">Add Product</button>
            </form>
        </div>
    </div>
    <% } %>

    <script src="<%= request.getContextPath() %>/js/home.js?v=4"></script>

</body>
</html>
