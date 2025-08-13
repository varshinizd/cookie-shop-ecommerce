package model;

public class Cart {
    private int id;
    private String username;
    private String productName;
    private int quantity;

    // Constructor
    public Cart(String username, String productName, int quantity) {
        this.username = username;
        this.productName = productName;
        this.quantity = quantity;
    }

    public Cart() {};

	// Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
