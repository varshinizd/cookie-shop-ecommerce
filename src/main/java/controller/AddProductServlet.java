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
	private static final long serialVersionUID = 1L;

	// Change this to your desired image storage path
	private static final String IMAGE_STORAGE_DIR = "C:/ProductImages";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int cost = Integer.parseInt(request.getParameter("cost"));
		String added_by = "admin";

		// Get uploaded file
		Part filePart = request.getPart("image");
		String fileName = filePart.getSubmittedFileName();

		// Make sure directory exists
		File uploadDir = new File(IMAGE_STORAGE_DIR);
		if (!uploadDir.exists()) uploadDir.mkdirs();

		// Save image to disk
		String fullImagePath = IMAGE_STORAGE_DIR + File.separator + fileName;
		filePart.write(fullImagePath);

		// Store just the image name or relative path in DB
		String imagePath = fileName;

		// Save product to DB
		Product product = new Product(name, description, added_by, cost, imagePath);
		ProductDAO dao = new ProductDAO();
		dao.addProduct(product);

		// Fetch products to show
		List<Product> products = ProductDAO.getProducts(added_by);
		request.setAttribute("products", products);
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
		dispatcher.forward(request, response);
	}
}
