package com.techelevator.items;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class CandyStoreItemTests {
    private CandyStoreItem candyStoreItem;

    @Test
    public void candy_store_item_constructor_happy_path(){
        String[] testArray = new String[]{
                "CH",
                "C1",
                "Snuckers Bar",
                "1.35",
                "T"
        };
        candyStoreItem = new CandyStoreItem(testArray);
        Assert.assertEquals("Product type failed to match","CH",candyStoreItem.getProductType());
        Assert.assertEquals("Item ID failed to match","C1",candyStoreItem.getInventoryId());
        Assert.assertEquals("Product name failed to match","Snuckers Bar", candyStoreItem.getProductName());
        Assert.assertEquals("Product price failed to match",new BigDecimal("1.35"),candyStoreItem.getPrice());
        Assert.assertTrue("Wrapper status expected to return true",candyStoreItem.hasWrapper());


    }
}
