package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

// Step 8: All purchases must be audited to prevent theft

public class Audit {
	// Private members
	
	private String dateTimeString;
	private String machineAction;
	private BigDecimal beforeBalance = BigDecimal.valueOf(0.00);
	private BigDecimal afterBalance= BigDecimal.valueOf(0.00); 
	
	// Getters and Setters

	public String getDateTimeString() {
		return dateTimeString;
	}

	public String getMachineAction() {
		return machineAction;
	}

	public void setMachineAction(String machineAction) {
		this.machineAction = machineAction;
	}

	public BigDecimal getBeforeBalance() {
		return beforeBalance;
	}

	public void setBeforeBalance(BigDecimal beforeBalance) {
		this.beforeBalance = beforeBalance;
	}

	public BigDecimal getAfterBalance() {
		return afterBalance;
	}

	public void setAfterBalance(BigDecimal afterBalance) {
		this.afterBalance = afterBalance;
	}
	
	// Default constructor
	public Audit() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY hh:mm:ss a");
		LocalDateTime now = LocalDateTime.now();
		this.dateTimeString = now.format(formatter); // Date and time is always set to right now and is formatted 
		
	}

	// Methods
	public void logToFile() { // Each purchase generates a line in log.txt
		String auditFileName = "Log.txt";
		File auditFile = new File(auditFileName);
		String message = getDateTimeString() + " " + getMachineAction() + "$" + getBeforeBalance() + " $" + getAfterBalance();

		if (auditFile.exists()) { // Log is a running file, so we must append to it
			
			try (PrintWriter writer = new PrintWriter(new FileOutputStream(auditFile.getAbsoluteFile(), true))) { 
				writer.println(message);

			} catch (FileNotFoundException e) {
				System.out.println("Sorry, we cannot locate that file.");
			}
		}
	}
	
	// Step 10: Optional Sales Report 
	// Generated if they press 4 for hidden menu option
	public void produceNewSalesReport() throws IOException {
		
		String auditFileName = "Log.txt";
		File auditFile = new File(auditFileName);
		
		// File name always includes the date and time so each file is uniquely named
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM_dd_YYYY_hh_mm_ss_a"); // We chose this format to follow file naming guidelines
		LocalDateTime now = LocalDateTime.now();
		String formattedDate = now.format(formatter);
		
		String newFileName = "newSalesReportFile_" + formattedDate + ".txt"; 
		
		File newFile = new File(newFileName);
		
		newFile.createNewFile(); // Creates new file each time user calls hidden menu option
		BigDecimal totalSales = new BigDecimal(0);
		
		try (PrintWriter writer = new PrintWriter(newFile)) {

			for (VendingMachineItem item : InventoryLoader.getVendingMachineItems()) { // Loop through vending machine item list
				int counter = 0; // This counts how many times the item appears on the audit File
				try (Scanner scanner = new Scanner(auditFile)) {

					while(scanner.hasNextLine()) { 

						String line = scanner.nextLine(); 

						if (line.contains(item.getName()) && !line.contains("IS SOLD OUT")) { // If audit file contains item name, and purchase was made
							counter++; // Increase counter
							totalSales = totalSales.add(item.getPrice()); // Add to gross sales
						}

					}
					
				}
				writer.println(item.getName() + "|" + counter); // For each product, write a new line with name and number of times it was purchased
			
			}	writer.println("TOTAL GROSS SALES: $" + totalSales); // At the end of file, print the total gross sales
		}
	}

}
