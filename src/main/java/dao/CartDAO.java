package dao;

import model.Cart;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    // Add item to cart (if product exists for user, update quantity instead)
    public static boolean addToCart(Cart cart) {
        String checkSql = "SELECT quantity FROM cart WHERE username = ? AND product_name = ?";
        String insertSql = "INSERT INTO cart (username, product_name, quantity) VALUES (?, ?, ?)";
        String updateSql = "UPDATE cart SET quantity = quantity + ? WHERE username = ? AND product_name = ?";

        try (Connection conn = DBConnection.getConnection()) {

            // Check if product already in cart
            try (PreparedStatement ps = conn.prepareStatement(checkSql)) {
                ps.setString(1, cart.getUsername());
                ps.setString(2, cart.getProductName());
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    // Update quantity
                    try (PreparedStatement ups = conn.prepareStatement(updateSql)) {
                        ups.setInt(1, cart.getQuantity());
                        ups.setString(2, cart.getUsername());
                        ups.setString(3, cart.getProductName());
                        return ups.executeUpdate() > 0;
                    }
                }
            }

            // Insert new row
            try (PreparedStatement ins = conn.prepareStatement(insertSql)) {
                ins.setString(1, cart.getUsername());
                ins.setString(2, cart.getProductName());
                ins.setInt(3, cart.getQuantity());
                return ins.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get all items in a user's cart
    public static List<Cart> getCartByUsername(String username) {
        List<Cart> cartItems = new ArrayList<>();
        String sql = "SELECT * FROM cart WHERE username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cart cart = new Cart();
                cart.setId(rs.getInt("id"));
                cart.setUsername(rs.getString("username"));
                cart.setProductName(rs.getString("product_name"));
                cart.setQuantity(rs.getInt("quantity"));
                cartItems.add(cart);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

    // Update quantity for a cart item
    public static boolean updateQuantity(String username, String productName, int quantity) {
        String sql = "UPDATE cart SET quantity = ? WHERE username = ? AND product_name = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, quantity);
            ps.setString(2, username);
            ps.setString(3, productName);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Remove one product from cart
    public static boolean removeFromCart(String username, String productName) {
        String sql = "DELETE FROM cart WHERE username = ? AND product_name = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, productName);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Clear the entire cart for a user
    public static boolean clearCart(String username) {
        String sql = "DELETE FROM cart WHERE username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
