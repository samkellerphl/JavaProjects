package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class Calculator {
	
	// Design decision to use Big Decimal
	// Private final variables for coins
	private final BigDecimal QUARTERS = new BigDecimal(.25); 
	private final BigDecimal DIMES = new BigDecimal(.10).setScale(2, RoundingMode.DOWN);
	private final BigDecimal NICKELS = new BigDecimal(.05).setScale(2, RoundingMode.DOWN);

	// Private members
	private BigDecimal currentBalance = new BigDecimal(0.00);
	private Audit logger = new Audit();


	// Getters and Setters

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance.setScale(2, RoundingMode.HALF_DOWN);
	}

	// Default Constructor
	public Calculator() {
		
	}

	// Methods
	public boolean acceptMoney(String moneyAmountstr){
		
		boolean isMoneyAccepted = false; // This variable returns whether the money was accepted or not
			
			if (moneyAmountstr.equals("1") || moneyAmountstr.equals("2") 
					|| moneyAmountstr.equals("5") || moneyAmountstr.equals("10")) { // Only accepted amounts (1, 2, 5, 10)
				// If input was valid, parse into an integer and then convert to BigDecimal
				Integer moneyAmount = Integer.parseInt(moneyAmountstr); 
				BigDecimal bdMoneyAmount = new BigDecimal(moneyAmount).setScale(2, RoundingMode.HALF_DOWN); 
				currentBalance = currentBalance.add(bdMoneyAmount); // Set balance to the amount entered plus current balance
				System.out.println("Current Money Provided: $" + currentBalance); // Tell user what the balance is
				logger.setMachineAction("FEED MONEY: "); // Update info for audit  file
				logger.setBeforeBalance(bdMoneyAmount);
				logger.setAfterBalance(currentBalance);
				isMoneyAccepted = true;

			}
			else { 
				System.out.println("I only accept bills including $1, $2, $5, $10");
				logger.setMachineAction("FEED MONEY FAILED "); // Update info for audit file
			} 

			logger.logToFile(); // Write to audit file
			return isMoneyAccepted; 
	}

	public BigDecimal makeChange() {
		

		int quarters = 0;
		int dimes = 0;
		int nickels = 0;

		System.out.println("Your change is : $" + currentBalance);
		BigDecimal beforeBalance = currentBalance; // Save before balance to update our audit file at the end
		
		// Keep making change until current balance is zero
		while (currentBalance.compareTo(BigDecimal.ZERO) == 1) {

			if (currentBalance.compareTo(QUARTERS) >= 0) { // If balance is larger than 25 cents
				quarters = (currentBalance.divide(QUARTERS)).intValue();
				currentBalance = currentBalance.subtract(QUARTERS.multiply(BigDecimal.valueOf(quarters))); 

			} else if (currentBalance.compareTo(DIMES) >= 0) { // If balance is larger than 10 cents
				dimes = (currentBalance.divide(DIMES)).intValue();
				currentBalance = currentBalance.subtract(DIMES.multiply(BigDecimal.valueOf(dimes))); 

			} else if (currentBalance.compareTo(NICKELS) >= 0) { // If balance is larger than 5 cents
				nickels = (currentBalance.divide(NICKELS)).intValue();
				currentBalance = currentBalance.subtract(NICKELS.multiply(BigDecimal.valueOf(nickels))); 
			}
		}

		System.out.println("Your change is " + quarters + " quarters, " + dimes + " dimes, and " + nickels + " nickels.");
		
		logger.setMachineAction("GIVE CHANGE: "); // Update info for audit file
		logger.setBeforeBalance(beforeBalance);
		logger.setAfterBalance(BigDecimal.valueOf(0.00).setScale(2, RoundingMode.DOWN));
		logger.logToFile(); // Write to audit file
		
		return currentBalance; // Return new balance, should always be 0
	}
	
	
}

