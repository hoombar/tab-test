package net.rdyonline.theappbusinesstest.data;

/**
 * Created by rdy on 11/01/15.
 */
public class Employee {

    private String name;
    private String role;
    private String image;
    private String description;

    public Employee(String name, String role, String image, String description) {
        this.name = name;
        this.role = role;
        this.image = image;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }
}
