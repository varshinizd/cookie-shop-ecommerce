package controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException
	{
		String name = request.getParameter("name");
		
		if(name != null)
		{
				ProductDAO dao = new ProductDAO();
				try {
					dao.deleteProduct(name);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		response.sendRedirect("home"); // redirect to HomeServlet


	}
}