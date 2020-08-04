package Model;

public class Product_Category {
    private int id;
    private String name;
    private String picture;

    public Product_Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    //Nom afficher pour la liste des commerciaux.
    @Override
    public String toString() {
        return this.getName();
    }
}
