<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, model.Product, dao.ProductDAO" %>
<%@ page session="true" %>

<%
    String productName = request.getParameter("name");
    Product product = null;

    if (productName != null && !productName.trim().isEmpty()) {
        product = ProductDAO.getProductByName(productName); // Implement this method in DAO
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= (product != null) ? product.getName() : "Product Not Found" %></title>
    <link rel="stylesheet" href="css/productPage.css?v=1">
</head>
<body>

<% if (product != null) { %>
    <div class="product-container">
        <img src="image?name=<%= product.getImagePath() %>" alt="<%= product.getName() %>" class="product-image"/>

        <h2 class="product-name"><%= product.getName() %></h2>

        <p class="product-price">Price: ₹ <strong><%= product.getCost() %></strong></p>

        <form action="addToCart" method="post" class="product-form">
            <input type="hidden" name="name" value="<%= product.getName() %>">

            <label for="quantity">Quantity:</label>
            <input type="number" name="quantity" id="quantity" value="1" min="1" required>

            <div class="buttons">
                <button type="submit">Add to Cart 🛒</button>
                <button type="submit" formaction="buyNow">Buy Now ⚡</button>
            </div>
        </form>
    </div>
<% } else { %>
    <h2>Product not found.</h2>
    <a href="home.jsp">← Back to Home</a>
<% } %>

</body>
</html> based on this 