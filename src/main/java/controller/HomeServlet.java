package controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;
import model.Product;
import model.User;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String username = (user != null) ? user.getUsername() : "guest";

        // Fetch products from DAO
        List<Product> products = ProductDAO.getProducts(username); 

        request.setAttribute("products", products);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}

