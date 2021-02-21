package com.techelevator;

import java.math.BigDecimal;

public abstract class VendingMachineItem { // Abstract class has children subclasses (candy, gum, beverages, chips)
											//but it is never instantiated 
	// Step 1: Each Vending Machine Item has a name, price, quantity, sound, slot location. 
	
	// Private Members
	private static final int DEFAULT_QUANTITY = 5; // All vending machine items start with a default quantity of 5 per slot location
	
	private String slotLocation; 
	private String name;
	private String sound;
	private int quantity = DEFAULT_QUANTITY;
	private BigDecimal price;
	
	
	// Getters and Setters
	
	public String getSlotLocation() {
		return slotLocation;
	}
	
	public void setSlotLocation(String slotLocation) {
		this.slotLocation = slotLocation;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSound() {
		return sound;
	}
	
	public void setSound(String sound) {
		this.sound = sound;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	

	// Default constructor
	public VendingMachineItem() {
		this.slotLocation = "";
		this.name = "";
		this.sound = "";
		this.price = new BigDecimal(0.00);
	}
	
	

}
