package com.org.ibs.model;

public class Purchase {
    private String productName;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Purchase(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }
}
