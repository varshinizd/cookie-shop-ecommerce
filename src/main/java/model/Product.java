package model;

public class Product {
    private int id;
    private String name;
    private String description;
    private String addedBy;
    private int  cost;
    private String imagePath;

    // Constructors
    public Product(String name, String description, String addedBy, int cost, String imagePath) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.addedBy = addedBy;
        this.imagePath = imagePath;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getCost() {return cost; }
    public String getAddedBy() { return addedBy; }
    public String getImagePath() {return imagePath;}
    
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setCost(int cost) {this.cost = cost;}
    public void setImagePath(String imagePath) {this.imagePath = imagePath;}
    public void setAddedBy(String addedBy) { this.addedBy = addedBy; }
}
