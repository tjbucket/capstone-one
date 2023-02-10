package com.techelevator.filereader;

import com.techelevator.items.CandyStoreItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

public class InventoryFileReaderTests {
     private InventoryFileReader inventoryFileReader;

     @Before
    public void setup(){
         inventoryFileReader = new InventoryFileReader();
         try(PrintWriter writer = new PrintWriter(new File("TestInventory.csv"))){
             writer.println("CH|C1|Snuckers Bar|1.35|T");
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
     }

     @Test
    public void read_file_happy_path() {
         List<CandyStoreItem> resultList = inventoryFileReader.readFile("TestInventory.csv");
         Assert.assertEquals("CH",resultList.get(0).getProductType());
         Assert.assertEquals("C1",resultList.get(0).getInventoryId());
         Assert.assertEquals("Snuckers Bar",resultList.get(0).getProductName());
         Assert.assertEquals(new BigDecimal("1.35"),resultList.get(0).getPrice());
         Assert.assertTrue(resultList.get(0).hasWrapper());
     }
}
