<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Enter OTP</title>
    <link rel="stylesheet" href="css/enterOtp.css">
    <script src="js/enterOtp.js" defer></script> <!-- Link the external JS file -->
</head>
<body>
    <div class="container">
        <h2>Enter OTP</h2>

        <%
            String error = (String) request.getAttribute("errorMessage");
            if (error != null) {
        %>
            <p class="error-message"><%= error %></p>
        <% 
            }
        %>

        <form id="otpForm" onsubmit="gatherOtpAndSubmit(event)" method="post" action="verifyOtp">

            <div class="otp-boxes">
                <input type="text" id="otp1" maxlength="1" oninput="moveToNext(this, 'otp2')" onpaste="handlePaste(event)" required>
                <input type="text" id="otp2" maxlength="1" oninput="moveToNext(this, 'otp3')" required>
                <input type="text" id="otp3" maxlength="1" oninput="moveToNext(this, 'otp4')" required>
                <input type="text" id="otp4" maxlength="1" oninput="moveToNext(this, 'otp5')" required>
                <input type="text" id="otp5" maxlength="1" oninput="moveToNext(this, 'otp6')" required>
                <input type="text" id="otp6" maxlength="1" required>
            </div>

            <input type="submit" value="Verify OTP">
        </form>
    </div>
</body>
</html>
