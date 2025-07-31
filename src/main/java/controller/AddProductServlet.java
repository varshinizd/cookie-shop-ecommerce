package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.User;
import model.Product;
import dao.ProductDAO;

@MultipartConfig
@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int cost = Integer.parseInt(request.getParameter("cost"));
		String added_by = "admin";

		// Image
		Part filePart = request.getPart("image");
		String fileName = filePart.getSubmittedFileName();

		// Get actual path to "images" folder inside webapp
		String uploadPath = getServletContext().getRealPath("/") + "images";
		System.out.println("Upload path: " + uploadPath);

		// Create directory if not exists
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) uploadDir.mkdirs();

		// Write file
		String fullImagePath = uploadPath + File.separator + fileName;
		filePart.write(fullImagePath);

		// Save only relative path for JSP usage
		String imagePath = "images/" + fileName;

		// Store in DB
		Product product = new Product(name, description, added_by, cost, imagePath);
		ProductDAO dao = new ProductDAO();
		dao.addProduct(product);

		// Forward to JSP
		List<Product> products = ProductDAO.getProducts(added_by);
		request.setAttribute("products", products);
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}
}
