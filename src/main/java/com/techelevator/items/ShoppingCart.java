package com.techelevator.items;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private BigDecimal totalMoneyAdded = new BigDecimal(0);
    private BigDecimal totalCostOfProducts = new BigDecimal(0);
    private Map<String, Integer> productMap = new HashMap<>();

    public void addToProductMap(String desiredProduct, int quantity){
        productMap.put(desiredProduct,quantity);
    }

    public void addToProductCost(BigDecimal cost){
        totalCostOfProducts.add(cost);
    }

    public void addTotalMoney(BigDecimal input){
        totalMoneyAdded.add(input);
    }

    public BigDecimal currentCustomerBalance(){
        return totalMoneyAdded.subtract(totalCostOfProducts);
    }

    public void checkout(Inventory inventory) {
        //TODO checkout
    }
}
