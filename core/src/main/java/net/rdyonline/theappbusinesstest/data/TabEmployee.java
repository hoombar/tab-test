package net.rdyonline.theappbusinesstest.data;

/**
 * Created by rdy on 11/01/15.
 */
public class TabEmployee implements Employee {

    private String name;
    private String role;
    private String image;
    private String description;

    public TabEmployee(String name, String role, String image, String description) {
        this.name = name;
        this.role = role;
        this.image = image;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
