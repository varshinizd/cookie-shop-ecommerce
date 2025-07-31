package controller;
import dao.UserDAO;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDAO.authenticateUser(username, password);
        if (user != null) 
        {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            request.setAttribute("currentUser", user);
            response.sendRedirect(request.getContextPath() + "/home"); // Redirect to a secured page
        } 
        else 
        {
        	request.setAttribute("errorMessage", "Invalid username or password");
        	request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}