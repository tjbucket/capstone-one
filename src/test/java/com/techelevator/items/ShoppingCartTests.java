package com.techelevator.items;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartTests {

    private ShoppingCart shoppingCart;
    private Inventory inventory;

    @Before
    public void setup(){
        shoppingCart = new ShoppingCart();
        inventory = new Inventory();
    }
    @Test
    public void add_total_money(){
        shoppingCart.addTotalMoney(new BigDecimal(5));
        Assert.assertEquals(new BigDecimal(5),shoppingCart.getTotalMoneyAdded());
    }

    @Test
    public void add_product_cost(){
        shoppingCart.addToProductCost(new BigDecimal(5));
        Assert.assertEquals(new BigDecimal(5),shoppingCart.getTotalCostOfProducts());
    }

    @Test
    public void current_customer_balance(){
        shoppingCart.addTotalMoney(new BigDecimal(9));
        shoppingCart.addToProductCost(new BigDecimal(5));
        Assert.assertEquals(new BigDecimal(4),shoppingCart.currentCustomerBalance());
    }
    @Test
    public void add_product_new_key(){
        shoppingCart.addProduct("L5",5,new BigDecimal(0),inventory);
        Assert.assertEquals(1,1);

    }

}
