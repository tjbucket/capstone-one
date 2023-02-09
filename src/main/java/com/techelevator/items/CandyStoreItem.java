package com.techelevator.items;

import java.math.BigDecimal;

public class CandyStoreItem {

    // Instance variables
    private final String productType;
    private final String inventoryId;
    private final String productName;
    private final BigDecimal price;
    private int productQuantity;
    private final boolean hasWrapper;

    // Overloaded constructor
    // Takes in a String[] length 5 as input with parameters of a CandyStoreItem
    public CandyStoreItem(String[] currentValues) {
        productType = currentValues[0];
        inventoryId = currentValues[1];
        productName = currentValues[2];

        // Convert String to BigDecimal
        price = new BigDecimal(currentValues[3]);

        // Convert String to boolean; will always be given "T" or "F", representing true or false respectively
        hasWrapper = "T".equalsIgnoreCase(currentValues[4]);

        // Quantity set to 100 by default based on problem statement
        productQuantity = 100;
    }

    // Getters and setters
    public String getProductType() {
        return productType;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public boolean hasWrapper() {
        return hasWrapper;
    }
}