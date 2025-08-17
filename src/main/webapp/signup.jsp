<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/signup.css?v=1">
</head>
<body>
    <div class="container">
        <h2>Sign Up</h2>

		<% if (request.getAttribute("errorMessage") != null) { %>
		    <div style="color: red; text-align: center;">
		        <%= request.getAttribute("errorMessage") %>
		    </div>
		<% } %>

        <form action="<%= request.getContextPath() %>/signup" method="post">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" required>

            <label for="email">Email</label>
            <input type="email" name="email" id="email" required>

            <label for="password">Password</label>
            <input type="password" name="password" id="password" required>

            <input type="submit" value="Register">
        </form>
        
        <p>Already have an account? <a style = "color: #734d4d" href="login.jsp">Login</a></p>
    </div>
</body>
</html>
