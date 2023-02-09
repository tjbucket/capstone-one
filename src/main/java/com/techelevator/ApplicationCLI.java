package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.items.Inventory;
import com.techelevator.items.ShoppingCart;
import com.techelevator.view.CashRegister;
import com.techelevator.view.Menu;

public class ApplicationCLI {

	// probably should leave this method alone... and go do stuff in the run method....
	public static void main(String[] args) {
		ApplicationCLI cli = new ApplicationCLI();
		cli.run();
	}

	/**
	 * This is the main method that controls the flow of the program.. Probably could look at the review code for ideas of what to put here...
	 */
	public void run() {
		InventoryFileReader fileReader = new InventoryFileReader();
		Inventory inventory = new Inventory();
		Menu menu = new Menu();


		//Call InventoryFileReader to read inventory file
		inventory.addInventoryItems(fileReader.readFile("inventory.csv"));

		System.out.println("Welcome to Silver Shamrocks Candy Company");
		//while loop keeps menu up unless chosen to exit
		while(true){
			menu.displayMainMenu();
			System.out.println("Please choose 1, 2, or 3: ");
			// switch compares string to check for 1, 2, or 3 corresponding with different menu options
			switch (menu.menuNavigator()){
				// (1) Show Inventory
				case "1": inventory.displayInventory();
				continue;
//				 (2) Make Sale
				case "2":
					ShoppingCart cart = new ShoppingCart();
					while (true) {
						// Displays sale menu options, continually updates as cart balance changes
						menu.displaySaleMenu(cart.currentCustomerBalance());
						switch (menu.menuNavigator()){
							// 2a (1) Take Money
							case "1": menu.takeMoney(cart);
							// 2b (2) Select Products
							case "2": menu.selectProducts(inventory, cart);
							// 2c (3) Complete Sale
							case "3": cart.checkout(inventory);

						}
					}
					continue;
				// (3) Quit
				case "3":
					System.out.println("Thank you! Exiting program.");
					break;
				default:
					System.out.println("Input only accepts 1, 2, or 3. Please try again.");
			}
		}
		}



		//List <CandyStoreItem> bob = readFile ()


		// Good place to create references for UserInterface, Inventory class, and Register class.... (There should NEVER be more than one instance of these)


		//probably a great place to create a loop that manages the main menu and delegates all work to the other classes....
		// Hint: for the submenu, maybe a loop inside this main loop? If you break out of the sub-loop(sub-menu), you would reach the
		//outer loop....






	}

	//feel free to create private methods here if you are feeling up to it, so run() doesn't get so long...


}
