package com.example.sunshoe;

import java.io.Serializable;

public class shoe implements Serializable {
    private int product_id;
    private String product_name;
    private int product_price;
    private int product_size;
    private String product_image;
    private String brand;
    private String category;
    private String description;

    public shoe(int product_id, String product_name, int product_price, int product_size, String product_image, String brand, String category, String description) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_size = product_size;
        this.product_image = product_image;
        this.brand = brand;
        this.category = category;
        this.description = description;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public int getProduct_size() {
        return product_size;
    }

    public void setProduct_size(int product_size) {
        this.product_size = product_size;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
