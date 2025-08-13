package controller;

import dao.CartDAO;
import dao.ProductDAO;
import model.Cart;
import model.Product;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            // Not logged in, redirect to login page
            response.sendRedirect("login.jsp");
            return;
        }

        String username = user.getUsername();
        String productName = request.getParameter("name");
        String quantityStr = request.getParameter("quantity");
        int quantity;
        if (quantityStr == null || quantityStr.isEmpty()) {
            // default value
            quantity = 1;
        } else {
            quantity = Integer.parseInt(quantityStr);
        }

        if (productName == null || productName.trim().isEmpty()) {
            response.sendRedirect("home.jsp?error=Invalid+product");
            return;
        }

        // Get product details (optional if you only store product name in cart)
        Product product = ProductDAO.getProductByName(productName);
        if (product == null) {
            response.sendRedirect("home.jsp?error=Product+not+found");
            return;
        }
        
        Cart cart = new Cart(username, productName, quantity);
        // Add product to cart in DB
        boolean added = CartDAO.addToCart(cart);

        if (added) {
            response.sendRedirect("cart?success=Product+added+to+cart");
        } else {
            response.sendRedirect("cart?error=Could+not+add+product+to+cart");
        }
    }
}
