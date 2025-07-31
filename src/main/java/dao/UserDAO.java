package dao;

import model.User;
import util.DBConnection;
import java.sql.*;

public class UserDAO 
{
	public User authenticateUser(String username, String password) {
	    User user = null;
	    String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
	    
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        System.out.println("UserDAO1");
	        stmt.setString(1, username);
	        stmt.setString(2, password);
	        System.out.println("UserDAO2");
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                user = new User();
	                user.setId(rs.getInt("id"));
	                user.setUsername(rs.getString("username"));
	                user.setPassword(rs.getString("password"));
	                user.setEmail(rs.getString("email")); 
	                System.out.println("UserDAO3");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return user;
	}
	
    public boolean userExists(String email) {
        boolean exists = false;

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
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
    public boolean registerUser(User user) {
        if (userExists(user.getEmail())) {
            return false; // User already exists
        }

        boolean success = false;

        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());

            int rows = stmt.executeUpdate();
            success = rows > 0;

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }
}