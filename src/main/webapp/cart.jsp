<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, model.Cart" %>
<%@ page session="true" %>
<%
    List<Cart> cartItems = (List<Cart>) request.getAttribute("cartItems");
    String username = (String) session.getAttribute("username");
%>
<!DOCTYPE html>
<html>
<head>
    <title>My Cart</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/cart.css?v=1">
</head>
<body>
<h1>Your Cart</h1>

<% if (cartItems == null || cartItems.isEmpty()) { %>
    <p class="empty-msg">Your cart is empty.</p>
<% } else { %>
    <table>
        <tr>
            <th>Product Name</th>
            <th>Quantity</th>
            <th>Action</th>
        </tr>
        <% for (Cart item : cartItems) { %>
            <tr>
                <td><%= item.getProductName() %></td>
                <td><%= item.getQuantity() %></td>
                <td>
                    <a href="<%= request.getContextPath() %>/removeFromCart?productName=<%= java.net.URLEncoder.encode(item.getProductName(), "UTF-8") %>" 
   class="btn">Remove</a>

                </td>
            </tr>
        <% } %>
    </table>
    <div class="checkout-container">
        <a href="checkout.jsp" class="btn">Checkout</a>
    </div>
<% } %>

</body>
</html>
