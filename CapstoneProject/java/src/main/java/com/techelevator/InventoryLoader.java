package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryLoader {
	
	// Private member
	
	private static List<VendingMachineItem> vendingMachineItems = new ArrayList<>();
	
	// Getter
	
	public static List<VendingMachineItem> getVendingMachineItems() {
		return vendingMachineItems; 
	}

	public static List<VendingMachineItem> displayVendingMachineItems() {
		
		// Step 5: When a customer selects display vending Machine Items, they are presented with this list 
		for (VendingMachineItem item : vendingMachineItems) {
			if (item.getQuantity() == 0) { // If item is sold out, display this
				System.out.println("Slot number: " + item.getSlotLocation() + " Name: " + item.getName() + " Price: $" + item.getPrice() + ". Sorry, we are all sold out.");
			} else if (item.getQuantity() > 0) { // otherwise display normally
				System.out.println("Slot number: " + item.getSlotLocation() + " Name: " + item.getName() + " Price: $" + item.getPrice() + ". There are " + item.getQuantity() + " remaining");
			}
		}
		return vendingMachineItems;
	}
	
	public static void categorizeVendingMachineItems() {
		
		// Step 3: Inventory Stocking via Input File when machine starts
		
		File inputFile = new File("vendingmachine.csv");

		try (Scanner scanFile = new Scanner(inputFile)) { //Try with resources scans vending machine item list doc

			while (scanFile.hasNextLine()) { // Loop through document

				String line = scanFile.nextLine(); // Save line
				String[] itemLine = line.split("\\|"); // Split line by | in an array

				if (line.startsWith("A")) { 
					Chips newChip = new Chips();
					newChip.setSlotLocation(itemLine[0]);
					newChip.setName(itemLine[1]);

					BigDecimal bdPrice = new BigDecimal(itemLine[2]); 
					newChip.setPrice(bdPrice);
					vendingMachineItems.add(newChip);

				} else if(line.startsWith("B")) {
					Candy newCandy = new Candy();
					newCandy.setSlotLocation(itemLine[0]);
					newCandy.setName(itemLine[1]);

					BigDecimal bdPrice = new BigDecimal(itemLine[2]); 
					newCandy.setPrice(bdPrice);
					vendingMachineItems.add(newCandy);

				} else if(line.startsWith("C")) {
					Beverages newBeverage = new Beverages();
					newBeverage.setSlotLocation(itemLine[0]);
					newBeverage.setName(itemLine[1]);

					BigDecimal bdPrice = new BigDecimal(itemLine[2]); 
					newBeverage.setPrice(bdPrice);
					vendingMachineItems.add(newBeverage);

				} else if(line.startsWith("D")) {
					Gum newGum = new Gum();
					newGum.setSlotLocation(itemLine[0]);
					newGum.setName(itemLine[1]);

					BigDecimal bdPrice = new BigDecimal(itemLine[2]); 
					newGum.setPrice(bdPrice);
					vendingMachineItems.add(newGum);

				} else { // if item entered is not item within the file
					System.out.println("Sorry, we don't know what type of item that is. Please make sure it starts with A - D."); // ?
				}

			}

		}
		catch (FileNotFoundException fnf) {
			fnf.printStackTrace();
		}
	}
	
	
	
}
