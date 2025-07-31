package controller;

import dao.UserDAO;
import model.User;
import util.MailUtil; // You’ll create this for sending mail

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Random;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        if (userDAO.userExists(email)) {
            request.setAttribute("errorMessage", "User with this email already exists.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        } else {
            // 1. Generate OTP
            String otp = String.valueOf(100000 + new Random().nextInt(900000)); // 6-digit OTP

            // 2. Send OTP to email
            boolean sent = MailUtil.sendOtp(email, otp);

            if (!sent) {
                request.setAttribute("errorMessage", "Failed to send OTP. Try again.");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
                return;
            }

            // 3. Store user and otp in session
            HttpSession session = request.getSession();
            session.setAttribute("pendingUser", user);
            session.setAttribute("otp", otp);

            // 4. Redirect to OTP entry page
            response.sendRedirect("enterOtp.jsp");
        }
    }
}
