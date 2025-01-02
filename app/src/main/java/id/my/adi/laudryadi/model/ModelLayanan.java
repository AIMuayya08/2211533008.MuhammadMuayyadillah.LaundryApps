package id.my.adi.laudryadi.model;

public class ModelLayanan {
    private String id; // Unique ID for the service
    private String name; // Name of the service (e.g., "Cuci Kering")
    private int price; // Price of the service

    public ModelLayanan() {
        // Default constructor
    }

    public ModelLayanan(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters and setters for id, name, and price

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // Method to get the service type (assuming 'name' represents the type)
    public String getTipe() {
        return name;
    }
}