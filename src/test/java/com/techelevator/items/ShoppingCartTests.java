package com.techelevator.items;

import com.techelevator.filereader.InventoryFileReader;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCartTests {

    private ShoppingCart shoppingCart = new ShoppingCart();

    @Before
    public void setup(){
        InventoryFileReader fileReader = new InventoryFileReader();
        Inventory inventory = new Inventory();
        inventory.addInventoryItems(fileReader.readFile("inventory.csv"));
    }

    @Test
    public void checkout_happy_path(){

    }


}
