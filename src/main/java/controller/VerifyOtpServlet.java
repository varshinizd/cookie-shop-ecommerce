package controller;

import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/verifyOtp")
public class VerifyOtpServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            request.setAttribute("errorMessage", "Session expired. Please try again.");
            request.getRequestDispatcher("enterOtp.jsp").forward(request, response);
            return;
        }

        String submittedOtp = request.getParameter("otp");
        String expectedOtp = (String) session.getAttribute("otp"); // for signup

        if (expectedOtp == null || !expectedOtp.equals(submittedOtp)) {
            request.setAttribute("errorMessage", "Invalid OTP. Please try again.");
            request.getRequestDispatcher("enterOtp.jsp").forward(request, response);
            return;
        }

        session.removeAttribute("otp");
        User pendingUser = (User) session.getAttribute("pendingUser");

        if (pendingUser != null) {
            userDAO.registerUser(pendingUser);
            session.removeAttribute("pendingUser");
            session.setAttribute("user", pendingUser); // login immediately
            response.sendRedirect("home.jsp");
            return;
        } else {
        	System.out.println("Redirecting to Dashboard2");
        	session.setAttribute("user", pendingUser);
            response.sendRedirect("home.jsp");
        }
    }
}
