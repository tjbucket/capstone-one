package com.techelevator.items;
import com.techelevator.filereader.LogFileWriter;
import com.techelevator.view.Menu;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private BigDecimal totalMoneyAdded = new BigDecimal(0);
    private BigDecimal totalCostOfProducts = new BigDecimal(0);
    private Map<String, Integer> productMap = new HashMap<>();
    private LogFileWriter auditLog = new LogFileWriter();
    private Menu moneyFormatter = new Menu();

    public void addProduct(String desiredProduct, int quantity, BigDecimal cost, Inventory inventory){
        if (!(productMap.containsKey(desiredProduct))) {
            productMap.put(desiredProduct, quantity);
        } else {
            productMap.put(desiredProduct, quantity+productMap.get(desiredProduct));
        }
        addToProductCost(cost);
        String addedCost = moneyFormatter.currencyFormat(cost);
        String newBalance = moneyFormatter.currencyFormat(currentCustomerBalance());
        auditLog.appendLogFile(quantity + " " +inventory.getInventory().get(desiredProduct).getProductName() + " " + desiredProduct + " " + addedCost + " " + newBalance);

    }

    public void addToProductCost(BigDecimal cost){
        totalCostOfProducts = totalCostOfProducts.add(cost);
    }

    public void addTotalMoney(BigDecimal input){
        totalMoneyAdded = totalMoneyAdded.add(input);
        auditLog.appendLogFile("MONEY RECEIVED: " + moneyFormatter.currencyFormat(input) + moneyFormatter.currencyFormat(currentCustomerBalance()));
    }

    public BigDecimal currentCustomerBalance(){
        return totalMoneyAdded.subtract(totalCostOfProducts);
    }

    public void checkout(Inventory inventory) {
        //TODO checkout
        for (Map.Entry<String, Integer> mapSet : productMap.entrySet()) {
            System.out.println(mapSet.getValue() + "|   2" + inventory.getInventory().get(mapSet.getKey()).getProductName());
        }
    }
}
