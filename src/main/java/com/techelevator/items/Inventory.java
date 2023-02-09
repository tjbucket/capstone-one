package com.techelevator.items;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {

    // Instance variable
    private Map<String, CandyStoreItem> inventory = new HashMap<>();

    // Default constructor
    public Inventory() {
    }

    // Methods
    public void addInventoryItems(List<CandyStoreItem> candyStoreItemList) {
        for (CandyStoreItem singleCandyStoreItem : candyStoreItemList) {
            inventory.put(singleCandyStoreItem.getInventoryId(), singleCandyStoreItem);
        }
    }

    public void displayInventory() {
        String banner = "Id Name Wrapper Qty Price";
        System.out.println(banner);

        // Get all keys (inventory IDs) from the map and put into a String[]
        // Sorting the String[] of keys will give alphabetical order
        String[] sortedKeys = inventory.keySet().toArray(new String[0]);
        Arrays.sort(sortedKeys);

        // Create empty String[] to hold each column of data for display
        String[] columnId = new String[sortedKeys.length];
        String[] columnName = new String[sortedKeys.length];
        String[] columnWrapper = new String[sortedKeys.length];
        String[] columnQty = new String[sortedKeys.length];
        String[] columnPrice = new String[sortedKeys.length];

        for (int i = 0; i < sortedKeys.length; i++) {
            columnId[i] = inventory.get(sortedKeys[i]).getInventoryId();
            columnName[i] = inventory.get(sortedKeys[i]).getProductName();

            // Change boolean to "Y" or "N"
            columnWrapper[i] = inventory.get(sortedKeys[i]).hasWrapper() ? "Y" : "N";

            // Change quantity from int to String, unless it is 0, then change to "Sold Out"
            columnQty[i] = inventory.get(sortedKeys[i]).getProductQuantity() == 0 ? "Sold Out" : String.valueOf(inventory.get(sortedKeys[i]).getProductQuantity());

            // Change price from BigDecimal to String
            columnPrice[i] = inventory.get(sortedKeys[i]).getPrice().toString();
        }

        for (int i = 0; i < sortedKeys.length; i++) {
            System.out.println(columnId[i] + columnName[i] + columnWrapper[i] + columnQty[i] + columnPrice[i]);
        }

        //TODO format String columns to have equal spacing

    }

    //Return false means there was insufficient stock for requested item
    //Return true means there was sufficient stock for requested item and stock has been appropriately adjusted
    public boolean selectInventoryItem(String inventoryId, int quantity) {

        //Get current inventory stock for given candy ID
        int currentStock = inventory.get(inventoryId).getProductQuantity();

        //If current inventory stock is greater than or equal to requested quantity, subtract that quantity from inventory
        if (currentStock >= quantity) {
            inventory.get(inventoryId).setProductQuantity(currentStock - quantity);
            return true;
        } else {
            return false;
        }
    }


    //Getters
    public Map<String, CandyStoreItem> getInventory() {
        return inventory;
    }


}
