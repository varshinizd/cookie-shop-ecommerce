<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Forgot Password</title>
    <link rel="stylesheet" href="css/forgotpassword.css">
</head>
<body>
    <div class="container">
        <h2>Forgot Password</h2>
        <form action="forgotpassword" method="post">
            <label for="email">Registered Email</label>
            <input type="email" id="email" name="email" required>

            <input type="submit" value="Send OTP">
        </form>

        <% String error = (String) request.getAttribute("errorMessage");
           if (error != null) { %>
            <div class="error-message"><%= error %></div>
        <% } %>
    </div>
</body>
</html>
