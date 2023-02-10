package com.techelevator.view;

import com.techelevator.items.Inventory;
import com.techelevator.items.ShoppingCart;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Scanner;

public class Menu {

    private Scanner menuScanner = new Scanner(System.in);

    public String menuNavigator(){
        return menuScanner.nextLine();
    }

    public void displayMainMenu(){
        System.out.println();
        System.out.println("(1) Show Inventory");
        System.out.println("(2) Make Sale");
        System.out.println("(3) Quit");
        System.out.print("Please enter a choice: ");
    }

    public void displaySaleMenu(BigDecimal currentCustomerBalance){
        System.out.println();
        System.out.println("(1) Take Money");
        System.out.println("(2) Select Products");
        System.out.println("(3) Complete Sale");
        System.out.println("Current Customer Balance: " + currencyFormat(currentCustomerBalance));
        System.out.print("Please enter a choice: ");
    }
    // Takes any BigDecimal number and formats it as a string displaying it as USD
    public String currencyFormat(BigDecimal n){
        return NumberFormat.getCurrencyInstance().format(n);
    }

    public void takeMoney(ShoppingCart cart){
        BigDecimal amountAllowed = new BigDecimal(100);
        // Amount to check ensures that the current balance is less than 1000, and changes amount allowed to the maximum
        BigDecimal amountToCheck = new BigDecimal(1000).subtract(cart.currentCustomerBalance());
        if((amountToCheck.compareTo(amountAllowed)) == -1){
            amountAllowed = amountToCheck;
        }
        System.out.print("Please enter an amount up to " + currencyFormat(amountAllowed)+":");
        //TODO Convert back to int, change from currency format, mention whole numbers.
        BigDecimal inputNumber = new BigDecimal(Double.parseDouble(menuNavigator()));
        if(inputNumber.compareTo(amountAllowed) <= 0) {
            cart.addTotalMoney(inputNumber);
        } else {
            System.out.println("Amount added is invalid. Please try again.");
        }
    }

    public void selectProducts(Inventory inventory, ShoppingCart cart) {
        inventory.displayInventory();
        System.out.println("Please use the item ID found in the first column to make a selection: ");
        String candySelection = menuNavigator().toUpperCase();
        if (!(inventory.getInventory().containsKey(candySelection))) {
            System.out.println("Item ID not found. Please try again.");
        }
        //TODO Fix me here!
        System.out.println("Please add a quantity: ");
        int quantityRequested = Integer.parseInt(menuNavigator());
                                                                         //Are you proud of me Dylan?
        BigDecimal possibleItemCost = ((new BigDecimal(quantityRequested).multiply(inventory.getInventory().get(candySelection).getPrice())));
        //Check valid input for quantity and ensures price is less than or equal to customer balance
        if (quantityRequested < 0) {
            System.out.println("Quantity must be greater than 0. Please try again.");
        } else if ((possibleItemCost.compareTo(cart.currentCustomerBalance())) <= 0) {
            if (inventory.selectInventoryItem(candySelection, quantityRequested)) {
                cart.addProduct(candySelection, quantityRequested, possibleItemCost, inventory);
            } else {
                System.out.println("Amount requested is greater than amount available. Please try again.");
            }
        } else {
            System.out.println("Price of candy exceeds customer balance. Please try again.");
        }
    }
    // If (1) Show Inventory option is selected, there's a displayInventory() method
    // Interact with inventory map
    // Format the output here (instead of Inventory class)

    // If (2) Make sale is selected, now there's a sub-menu to choose
    // (2a) Take Money
        // Interact with shopping cart
    // (2b) Select Products
        // Interact with Inventory
        // Interact with shopping cart
    // (2c) Complete sale
        // Interact with cash register (which will pull from shopping cart)
        // After cash register interaction, breaks to main menu loop
        // Consider the edge case of nothing in the shopping cart, and still want to break out to main menu... where to implement this
    // Display current customer balance $
    // Need while loop to stay in this submenu (until (2c) Complete sale is selected)


    // If (3) Quit is selected, break statement with message

}