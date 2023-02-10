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
        System.out.printf("Please enter a whole dollar amount up to $%.0f: ", amountAllowed);
        BigDecimal inputNumber;
        while(true){
            try{
                inputNumber = new BigDecimal(Integer.parseInt(menuNavigator().trim()));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Quantity must be inputted as an integer. Please input another number.");
            }
        }

        if(inputNumber.compareTo(amountAllowed) <= 0) {
            cart.addTotalMoney(inputNumber);
        } else {
            System.out.println("Amount added is invalid. Please try again.");
        }
    }
    public void selectProducts(Inventory inventory, ShoppingCart cart) {
        // Displays choices from inventories and captures customer selection
        inventory.displayInventory();
        System.out.print("Please use the item ID found in the first column to make a selection: ");
        String candySelection = menuNavigator().toUpperCase();
        // Checks to see if customer selection is contained within inventory map
        if (!(inventory.getInventory().containsKey(candySelection))) {
            System.out.println("Item ID not found. Please try again.");
        } else {
            int quantityRequested;
            System.out.print("Please choose a quantity to purchase. To cancel, enter zero: ");
            while(true){
                try{
                    quantityRequested = Integer.parseInt(menuNavigator().trim());
                    if (quantityRequested < 0){
                        System.out.println("Number must be greater than or equal to zero. Please enter a new number");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e){
                    System.out.println("Quantity must be inputted as an integer. Please try again.");
                }
            }
            // Returns them directly to submenu if they input 0
            if (quantityRequested == 0) {
                System.out.println("Returning you to menu.");
                return;
            }
            // Calculates the total cost for the given amount of candy
            BigDecimal possibleItemCost = ((new BigDecimal(quantityRequested).multiply(inventory.getInventory().get(candySelection).getPrice())));
            // Check valid input for quantity and ensures price is less than or equal to customer balance
            if ((possibleItemCost.compareTo(cart.currentCustomerBalance())) <= 0) {
                // selectInventoryItem method attempts to subtract the amount requested from the total pieces of the desired candy.
                // It will return true if the operation is successful and false otherwise.
                if (inventory.selectInventoryItem(candySelection, quantityRequested)) {
                    cart.addProduct(candySelection, quantityRequested, possibleItemCost, inventory);
                } else {
                    System.out.println("Amount requested is greater than amount available. Please try again.");
                }
            } else {
                System.out.println("Price of candy exceeds customer funds. Please try again.");
            }
        }
    }
}