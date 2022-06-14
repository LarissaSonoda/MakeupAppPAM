package com.example.appmakeuppam;

import java.io.Serializable;

public class Product implements Serializable {
    private String Name, Price, Description, Brand, Product_type;
    private byte[] Image;

    public Product(){}

    public Product(String name, String price, String description, String brand, String product_type, byte[] image){
        Name = name;
        Price = price;
        Description = description;
        Brand = brand;
        Product_type = product_type;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public String getPrice() {
        return Price;
    }

    public String getDescription() {
        return Description;
    }

    public String getBrand() {
        return Brand;
    }

    public String getProduct_type() {
        return Product_type;
    }

    public byte[] getImage() {
        return Image;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public void setProduct_type(String product_type) {
        Product_type = product_type;
    }

    public void setImage(byte[] image) {
        Image = image;
    }
}
