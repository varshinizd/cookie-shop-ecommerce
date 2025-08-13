package dao;
import java.sql.*;
import java.util.*;
import model.Product;
import util.DBConnection;

public class ProductDAO {
    public static List<Product> getProducts(String username) {
        List<Product> list = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection(); 
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM products");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product(
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("added_by"),
                    rs.getInt("cost"),
                    rs.getString("imagePath")
                );
                list.add(p);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean productExists(String name) {
        boolean exists = false;

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM products WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) 
            {
                exists = true;
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }
    
    public boolean addProduct(Product product)
    {
    	if(productExists(product.getName()))
    	{
    		return false;
    	}
    	boolean success = false;
    	try 
    	{
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO products (name, description, cost, added_by, imagePath) "
            		+ "VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setInt(3, product.getCost());
            stmt.setString(4, product.getAddedBy());
            stmt.setString(5, product.getImagePath());
            
            int rows = stmt.executeUpdate();
            success = rows > 0;
            stmt.close();
            conn.close();
            System.out.println("Product added to DB1");
        } catch (Exception e) 
    	{
            e.printStackTrace();
            System.out.print("Failed to add into db here");
        }
    	return success;
    }
    
    public boolean deleteProduct(String name) throws SQLException
    {
    	if(!productExists(name))
    	{
    		return false;
    	}
    	boolean success = false;
    	try
    	{
        	Connection conn = DBConnection.getConnection();
        	String sql = "DELETE FROM products WHERE name = ?";
        	PreparedStatement stmt = conn.prepareStatement(sql);
        	stmt.setString(1, name);
        	int rows = stmt.executeUpdate();
        	success = rows>0;
        	stmt.close();
        	conn.close();
        	System.out.println("Product deleted successfully");
    	}
    	catch(Exception e)
    	{
			e.printStackTrace();
            System.out.print("Failed to delete from the db here");
    	}
    	return success;
    }
    public static Product getProductByName(String name) {
        Product product = null;

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM products WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                product = new Product(
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("added_by"),
                    rs.getInt("cost"),
                    rs.getString("imagePath")
                );
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }
    
    public static List<Product> searchProducts(String keyword) {
        List<Product> products = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM products WHERE name LIKE ? OR description LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product(
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("added_by"),
                    rs.getInt("cost"),
                    rs.getString("imagePath")
                );
                products.add(p);
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

}
