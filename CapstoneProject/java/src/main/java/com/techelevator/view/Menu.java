package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

	// We made several methods and members protected because we created a child class "PurchaseMenu" 
	
	protected PrintWriter out; 
	protected Scanner in; 

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}
	

	protected Object getChoiceFromUserInput(Object[] options) { 
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	protected void displayMenuOptions(Object[] options) { // this used to be private 
		out.println();
		for (int i = 0; i < 3; i++) { // We changed options.length to 3 so that hidden menu option would not be displayed
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

}
