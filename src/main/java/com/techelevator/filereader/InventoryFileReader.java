package com.techelevator.filereader;

import com.techelevator.items.CandyStoreItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class would be a GREAT place to read in a file and return a data structure matching the one in
 * your Inventory class. (You probably saw something similar in some review code we did....)
 */
public class InventoryFileReader {

    public InventoryFileReader() {
    }

    public List<CandyStoreItem> readFile(String inputFile) {

        // inputFile is a String representing a read file path
        File inventoryFile = new File(inputFile);

        // Create empty List that will be filled with CandyStoreItem objects and then eventually returned
        List<CandyStoreItem> candyStoreItemList = new ArrayList<>();

        // Try-with-resources to manage the reader and automatically close inventoryFile when finished
        try (Scanner reader = new Scanner(inventoryFile)) {
            while (reader.hasNextLine()) {
                String currentLine = reader.nextLine();
                String[] currentValues = currentLine.split("\\|");

                // Passes String[] length 5 of CandyStoreItem parameters into CandyStoreItem constructor
                CandyStoreItem currentCandyStoreItem = new CandyStoreItem(currentValues);

                // Add each CandyStoreItem to the List
                candyStoreItemList.add(currentCandyStoreItem);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Sorry, the input file was unable to be read. Please try again.");
            e.printStackTrace();
        }
        return candyStoreItemList;
    }
}
