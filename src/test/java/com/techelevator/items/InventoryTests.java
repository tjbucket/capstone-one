package com.techelevator.items;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class InventoryTests {
    private Inventory inventory;
    private List<CandyStoreItem> testList;

    @Before
    public void setup(){
        inventory = new Inventory();
        testList = new ArrayList<>();
        testList.add(new CandyStoreItem("CH|C1|Snuckers Bar|1.35|T".split("\\|")));
        testList.add(new CandyStoreItem("SR|S1|Gummy Ants|.10|F".split("\\|")));
        testList.add(new CandyStoreItem("HC|H1|Jolly Cowboy|2.35|T".split("\\|")));
        testList.add(new CandyStoreItem("LI|L4|OK & Scarce|1.75|F".split("\\|")));
        testList.add(new CandyStoreItem("LI|L5|Good & Plenty|2.00|F".split("\\|")));
    }


    @Test
    public void add_inventory_items_happy_path(){
        inventory.addInventoryItems(testList);
        String[] testArray = new String[]{"C1","H1","L4","L5","S1"};
        Map<String, CandyStoreItem> testMap = inventory.getInventory();
        String[] actualArray = testMap.keySet().toArray(new String[0]);
        Arrays.sort(actualArray);
        Assert.assertArrayEquals(testArray,actualArray);
    }

    @Test
    public void select_inventory_item_sufficient_stock(){
        inventory.addInventoryItems(testList);
        Assert.assertTrue(inventory.selectInventoryItem("L5",49));
    }

    @Test
    public void select_inventory_item_insufficient_stock(){
        inventory.addInventoryItems(testList);
        Assert.assertFalse(inventory.selectInventoryItem("L5",101));
    }
}
