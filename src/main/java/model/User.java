package model;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;

    // Constructors
    public User() {}
    
    public User(String username, String email, String password) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail(){return this.email;}
    public void setEmail(String email) {this.email=email;}
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}