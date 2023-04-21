package com.techelevator.items;

import com.techelevator.filereader.LogFileWriter;
import com.techelevator.view.Menu;

import java.math.BigDecimal;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private BigDecimal totalMoneyAdded = new BigDecimal(0);
    private BigDecimal totalCostOfProducts = new BigDecimal(0);
    private Map<String, Integer> productMap = new HashMap<>();
    private Menu moneyFormatter = new Menu();

    public void addProduct(String desiredProduct, int quantity, BigDecimal cost, Inventory inventory) {
        if (!(productMap.containsKey(desiredProduct))) {
            productMap.put(desiredProduct, quantity);
        } else {
            productMap.put(desiredProduct, quantity + productMap.get(desiredProduct));
        }
        addToProductCost(cost);
        String addedCost = moneyFormatter.currencyFormat(cost);
        String newBalance = moneyFormatter.currencyFormat(currentCustomerBalance());
        LogFileWriter.appendLogFile(quantity + " " + inventory.getInventory().get(desiredProduct).getProductName() + " " + desiredProduct + " " + addedCost + " " + newBalance);

    }

    public void addToProductCost(BigDecimal cost) {
        totalCostOfProducts = totalCostOfProducts.add(cost);
    }

    public void addTotalMoney(BigDecimal input) {
        totalMoneyAdded = totalMoneyAdded.add(input);
        LogFileWriter.appendLogFile("MONEY RECEIVED: " + moneyFormatter.currencyFormat(input) + " " + moneyFormatter.currencyFormat(currentCustomerBalance()));
    }

    public BigDecimal currentCustomerBalance() {
        return totalMoneyAdded.subtract(totalCostOfProducts);
    }

    public void checkout(Inventory inventory) {

        // Create empty String[] to hold each column of data for display
        String[] columnQty = new String[productMap.size()];
        String[] columnName = new String[productMap.size()];
        String[] columnType = new String[productMap.size()];
        String[] columnUnitPrice = new String[productMap.size()];
        String[] columnSubtotalCost = new String[productMap.size()];

        // Track longest CandyStoreItem product name and product type lengths for formatting later in this method
        int maxNameLength = 0;
        int maxTypeLength = 0;

        // Create counter to track rows of data across columns in receipt
        int counter = 0;

        for (Map.Entry<String, Integer> singleEntry : productMap.entrySet()) {

            columnQty[counter] = String.valueOf(singleEntry.getValue());
            columnName[counter] = inventory.getInventory().get(singleEntry.getKey()).getProductName();

            // Get max length of product names
            if (columnName[counter].length() > maxNameLength) {
                maxNameLength = columnName[counter].length();
            }

            // Expand product type abbreviations to full words
            switch (inventory.getInventory().get(singleEntry.getKey()).getProductType()) {
                case "CH":
                    columnType[counter] = "Chocolate Confectionery";
                case "SR":
                    columnType[counter] = "Sour Flavored Candies";
                case "LI":
                    columnType[counter] = "Licorice and Jellies";
                case "HC":
                    columnType[counter] = "Hard Tack Confectionery";
            }

            // Get max length of product types
            if (columnType[counter].length() > maxTypeLength) {
                maxTypeLength = columnType[counter].length();
            }
            // Format BigDecimal to String with $
            BigDecimal bigDecimalUnitPrice = inventory.getInventory().get(singleEntry.getKey()).getPrice();
            columnUnitPrice[counter] = NumberFormat.getCurrencyInstance().format(bigDecimalUnitPrice);

            BigDecimal bigDecimalQty = new BigDecimal(singleEntry.getValue());
            columnSubtotalCost[counter] = NumberFormat.getCurrencyInstance().format(bigDecimalQty.multiply(bigDecimalUnitPrice));

            // Increment counter to next row of CandyStoreItem receipt data
            counter++;
        }


        // Styling format
        String columnQtyFormat = "%-3.3s"; // Fixed size 3 characters, left aligned
        String columnNameFormat = "%-" + maxNameLength + "." + maxNameLength + "s"; // Fixed size based on maxNameLength, left aligned
        String columnTypeFormat = "%-" + maxTypeLength + "." + maxTypeLength + "s"; // Fixed size based on maxNameLength, left aligned
        String columnUnitPriceFormat = "%-5.5s"; // Fixed size 5 characters, left aligned
        String columnTotalCostFormat = "%s"; // Fixed size 5 characters, left aligned


        String padBetweenColumns = "   "; // Fixed size 3 padding between columns

        // Create String of format info
        String formatInfo = columnQtyFormat + padBetweenColumns
                + columnNameFormat + padBetweenColumns
                + columnTypeFormat + padBetweenColumns
                + columnUnitPriceFormat + padBetweenColumns
                + columnTotalCostFormat;

        System.out.println();

        // Print all columns of data with format info
        for (int i = 0; i < columnQty.length; i++) {
            System.out.format(formatInfo, columnQty[i], columnName[i], columnType[i], columnUnitPrice[i], columnSubtotalCost[i]);
            System.out.println();
        }

        BigDecimal customerChange = totalMoneyAdded.subtract(totalCostOfProducts);

        // Only handles 7 denominations
        int[] countDenominations = new int[7];
        BigDecimal[] bigDecimals = new BigDecimal[]{
            new BigDecimal(20),
            new BigDecimal(10),
            new BigDecimal(5),
            new BigDecimal(1),
            new BigDecimal("0.25"),
            new BigDecimal("0.1"),
            new BigDecimal("0.05")
        };

        for (int i = 0; i < bigDecimals.length; i++) {
            countDenominations[i] = customerChange.divideToIntegralValue(bigDecimals[i]).intValue();
            customerChange = customerChange.remainder(bigDecimals[i]);
        }

        System.out.println();
        System.out.println("Total: " + NumberFormat.getCurrencyInstance().format(totalCostOfProducts));

        System.out.println();
        System.out.println("Change: " + NumberFormat.getCurrencyInstance().format(currentCustomerBalance()));


        if (countDenominations[0] > 0) {
            if (countDenominations[0] > 1) {
                System.out.println("(" + countDenominations[0] + ") Twenties");
            } else {
                System.out.println("(1) Twenty");
            }
        }
        for (int i = 1; i < countDenominations.length; i++) {
            if (countDenominations[i] > 0) {
                String newSegment = "(";
                switch (i) {
                    case 1:
                        newSegment = newSegment + countDenominations[i] + ") Ten";
                        break;
                    case 2:
                        newSegment = newSegment + countDenominations[i] + ") Five";
                        break;
                    case 3:
                        newSegment = newSegment + countDenominations[i] + ") One";
                        break;
                    case 4:
                        newSegment = newSegment + countDenominations[i] + ") Quarter";
                        break;
                    case 5:
                        newSegment = newSegment + countDenominations[i] + ") Dime";
                        break;
                    case 6:
                        newSegment = newSegment + countDenominations[i] + ") Nickel";
                        break;
                }
                if (countDenominations[i] > 1) {
                    newSegment += "s";
                }
                System.out.println(newSegment);
            }
        }

        LogFileWriter.appendLogFile("CHANGE GIVEN: "
                + NumberFormat.getCurrencyInstance().format(currentCustomerBalance()) + " "
                + NumberFormat.getCurrencyInstance().format(customerChange));

    }

    public BigDecimal getTotalMoneyAdded() {
        return totalMoneyAdded;
    }

    public BigDecimal getTotalCostOfProducts() {
        return totalCostOfProducts;
    }

    public Map<String, Integer> getProductMap() {
        return productMap;
    }
}
