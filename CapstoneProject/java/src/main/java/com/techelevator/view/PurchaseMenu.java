package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;


// Step 6: Purchasing process menu 

public class PurchaseMenu extends Menu{ // Child class of Menu to display purchase menu (feed money, select product, finish transaction)
	
	//Private members
	private BigDecimal balance = BigDecimal.ZERO; 
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public PurchaseMenu(InputStream input, OutputStream output, BigDecimal balance) {
		super(input, output);
		this.balance = balance;
		
	}
	
	@Override
	protected void displayMenuOptions(Object[] options) {  
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		System.out.println(); 
		// Now Current Balance print statement displays where we want it, because we overrided the parent method
		out.print(System.lineSeparator() + "Current Balance : $" + balance + "\n\nPlease choose an option >>> "); 
		out.flush();
	}

	
	
}
