package com.doanchung.assignmentand102.models;

public class Product {
    private int id;
    private String productName;
    private int productPrice;
    private int productNumber;

    public Product() {
    }

    public Product(int id, String productName, int productPrice, int productNumber) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productNumber = productNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }
}
