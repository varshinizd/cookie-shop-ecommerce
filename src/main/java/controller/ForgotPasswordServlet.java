package controller;

import dao.UserDAO;
import util.MailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Random;

@WebServlet("/forgotpassword")
public class ForgotPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        UserDAO userDAO = new UserDAO();

        // Check if email is registered
        if (!userDAO.userExists(email)) {
            request.setAttribute("errorMessage", "Email is not registered.");
            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
            return;
        }

        String otp = generateOTP();
        boolean mailSent = MailUtil.sendOtp(email, otp);

        if (mailSent) {
            HttpSession session = request.getSession();
            session.setAttribute("otp", otp);
            session.setAttribute("email", email);
            response.sendRedirect("enterOtp.jsp");
        } else {
            request.setAttribute("errorMessage", "Failed to send OTP. Please try again.");
            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
        }
    }
}
