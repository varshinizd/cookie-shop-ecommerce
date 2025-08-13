<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, model.Product, dao.ProductDAO, model.User" %>
<%@ page session="true" %>

<%
    String productName = request.getParameter("name");
    Product product = null;

    if (productName != null && !productName.trim().isEmpty()) {
        product = ProductDAO.getProductByName(productName);
    }

    User currentUser = (User) session.getAttribute("user"); // Assuming User object in session
    String role = (currentUser != null) ? currentUser.getUsername() : "guest"; // "admin" or "user"
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= (product != null) ? product.getName() : "Product Not Found" %></title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/productPage.css?v=4">
</head>
<body>

<% if (product != null) { %>
    <div class="product-page">
        <div class="left-column">
            <img src="image?name=<%= product.getImagePath() %>" alt="<%= product.getName() %>"/>
            <p class="product-price">₹ <%= product.getCost() %></p>
        </div>

        <div class="right-column">
            <h2 style="text-align:center;"><%= product.getName() %></h2>
            <div class="product-description" style="white-space: pre-wrap; line-height: 1.6; font-size: 1.1rem; text-align:left;">
                <%= product.getDescription() %>
            </div>

            <div class="buttons">
                <% if ("admin".equalsIgnoreCase(role)) { %>
                    <!-- Admin sees Delete button -->
                    <form action="deleteProduct" method="post" onsubmit="return confirm('Delete this product?');">
                        <input type="hidden" name="name" value="<%= product.getName() %>">
                        <button type="submit" class="delete-btn">Delete Product</button>
                    </form>
                <% } else { %>
                    <!-- Regular user sees Add to Cart and Buy Now -->
                    <form action="addToCart" method="post">
                        <input type="hidden" name="name" value="<%= product.getName() %>">
                        <button type="submit" class="add-to-cart">Add to Cart 🛒</button>
                    </form>

                    <form action="buyNow" method="post">
                        <input type="hidden" name="name" value="<%= product.getName() %>">
                        <button type="submit" class="buy-now">Buy Now ⚡</button>
                    </form>
                <% } %>
            </div>
        </div>
    </div>
<% } else { %>
    <h2>Product not found.</h2>
    <a href="home.jsp" class="back-link">← Back to Home</a>
<% } %>

</body>
</html>
