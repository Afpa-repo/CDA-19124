package Model;

import java.util.Date;

public class Product {
    private int id;
    private String libelle;
    private String description;
    private String color;
    private String picture;
    private double price;
    private int stock;
    private java.sql.Date created_at;
    private Product_Category product_category;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(java.sql.Date created_at) {
        this.created_at = created_at;
    }

    public Product_Category getProduct_category() {
        return product_category;
    }

    public void setProduct_category(Product_Category product_category) {
        this.product_category = product_category;
    }
}
