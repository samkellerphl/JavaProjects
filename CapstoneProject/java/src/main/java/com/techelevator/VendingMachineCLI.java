package com.techelevator;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;
import com.techelevator.view.Menu;
import com.techelevator.view.PurchaseMenu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_HIDDEN = "";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_HIDDEN };
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };

	private Menu menu;

	private static Calculator calc = new Calculator(); 
	private static Audit logger = new Audit(); 
	private static Scanner scanner = new Scanner(System.in); 


	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}


	public void run() {

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {

				InventoryLoader.displayVendingMachineItems(); // display vending machine items

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

				purchaseMenu(); // goes to our purchase menu

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(1); // exits the main menu
				
			} else if (choice.equals(MAIN_MENU_OPTION_HIDDEN)) {
				try {
					logger.produceNewSalesReport();
					System.out.println("New Sales Report created!");
				} catch (IOException e) {
					System.out.println("Something went wrong!");
					e.printStackTrace();
				}
			}
		}
	}


	public static void main(String[] args) {
		
		// Step 4: This is how we call our product list file and restock all items to have quantity of 5 
		InventoryLoader.categorizeVendingMachineItems();
		
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
		

		

	}

	// Step 7: Purchase Process Flow
	public static void purchaseMenu() {

		while (true) {
			PurchaseMenu purchaseMenu = new PurchaseMenu(System.in, System.out, calc.getCurrentBalance());
			
			// Step 6: Display Purchase Process Menu
			String purchaseChoice = (String) purchaseMenu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS); // displays purchase menu options

			if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) { 
				System.out.print("Please enter a whole dollar amount. I take bills up to $10 >>> ");
				String moneyAmountstr = scanner.nextLine(); 
				calc.acceptMoney(moneyAmountstr); // Takes in money from user
				VendingMachineCLI.purchaseMenu(); // Return to purchase menu
				
			} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
				InventoryLoader.displayVendingMachineItems(); // When user selects product, inventory displays
				System.out.print("\nEnter the slot number of the snack you would like to purchase >>> "); 
				String snack = scanner.nextLine(); 
				promptUserSnackChoice(snack); 


			} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
				calc.makeChange(); // Calls our makeChange method
				Menu returnMenu = new Menu(System.in, System.out); // Return to main menu
				VendingMachineCLI newCli = new VendingMachineCLI(returnMenu); 
				newCli.run(); // Start over from main menu display
			}
		}

	}

	public static boolean promptUserSnackChoice(String snack) {

			boolean dispensed = false; // Was the item actually dispensed?
			boolean outOfStock = false; // Was item out of stock
			boolean insufficientFunds = false; // Did they have enough money

			for (VendingMachineItem item : InventoryLoader.getVendingMachineItems()) { // Loop through list of items

				BigDecimal beforeBalance = calc.getCurrentBalance(); // Balance before transaction for logger
				
				
				// If user input is a valid slot location and item is in stock
				if (snack.equalsIgnoreCase(item.getSlotLocation()) == true && item.getQuantity() > 0) {
					
					
					// If they have enough money for chosen item, user may make purchase
					if (calc.getCurrentBalance().compareTo(item.getPrice()) == 1)  {
						item.setQuantity(item.getQuantity() - 1); // Item quantity decreased by 1
						calc.setCurrentBalance(calc.getCurrentBalance().subtract(item.getPrice())); // Updated balance
						System.out.println("You purchased "+ item.getName() + " for $" + item.getPrice() + ". New balance: $" + calc.getCurrentBalance());
						System.out.println(item.getSound());
						
						logger.setMachineAction(item.getName() + " " + item.getSlotLocation() + " "); // Update info for audit file
						logger.setBeforeBalance(beforeBalance);
						logger.setAfterBalance(calc.getCurrentBalance());

						dispensed = true;
					} 
					else {
						insufficientFunds = true;
						logger.setMachineAction("TRANSACTION FAILED "); // Update info for audit  file
						logger.setBeforeBalance(beforeBalance);
						logger.setAfterBalance(beforeBalance);
						
					}
					
				} else if (snack.equalsIgnoreCase(item.getSlotLocation()) == true && item.getQuantity() == 0) {
					outOfStock = true;
					logger.setMachineAction(item.getName() + " IS SOLD OUT! ");
					logger.setBeforeBalance(beforeBalance);
					logger.setAfterBalance(beforeBalance);
					
				} 

			} 

			if(!dispensed && insufficientFunds) { // If item was not dispensed and they had insufficient funds, print this
				System.out.println("Add more money please!");
			}

			if(!dispensed && !outOfStock && !insufficientFunds) { // If item was not dispensed, not out of stock, and they have money, print this
				System.out.println("Please enter a valid slot number!");
				logger.setMachineAction(" INVALID SLOT LOCATION! ");
			}

			if(!dispensed && outOfStock) { // If item was not dispensed and is out of stock, print this
				System.out.println("Sorry that item is sold out!");
			}

			logger.logToFile(); // Log to audit file
			purchaseMenu(); // Return to purchase menu
		
			return dispensed; // Return whether this item was dispensed or not 

	}


}
