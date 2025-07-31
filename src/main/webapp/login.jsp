<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/login.css?v=1">
</head>
<body>
    <div class="login-container">
        <h2>Login</h2>

        <%
            String error = (String) request.getAttribute("errorMessage");
            if (error != null) {
        %>
            <p class="error-message"><%= error %></p>
        <%
            }
        %>

        <form method="post" action="<%= request.getContextPath() %>/auth/login">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <input type="submit" value="Login">
        </form>

        <div class="extra-links">
            <a href="forgotpassword.jsp" style="color: #734d4d;">Forgot Password?</a><br>
            <p>Don't have an account? 
                <a href="signup.jsp" style="color: #734d4d;">Sign Up</a>
            </p>
        </div>
    </div>
</body>
</html>
