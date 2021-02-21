package com.techelevator;

import java.math.BigDecimal;

import org.junit.*;

public class ChipsTest {
	
	
	Chips chip = new Chips();
	
	
	
	@Test
	public void getSoundTest() {
		
		String actualSound = chip.getSound(); 
		String expectedSound = "Crunch Crunch, Yum!"; 
		
		Assert.assertEquals(expectedSound, actualSound); 
		
	}
	
	@Test
	public void getSound_02Test() {
		
		String actualSound = chip.getSound(); 
		String expectedSound = "Crunch Yum!"; 
		
		Assert.assertNotEquals(expectedSound, actualSound); 
		
	}
	
	@Test
	public void getPriceTest() {
		
		chip.setPrice(BigDecimal.valueOf(2));
		BigDecimal actualPrice = chip.getPrice();
		
		BigDecimal expectedPrice = BigDecimal.valueOf(2); 
		
		Assert.assertEquals(expectedPrice, actualPrice); 
		
	}
	
	@Test
	public void getSlotLocationTest() {
		
		chip.setSlotLocation("a4");
		String actualLocation = chip.getSlotLocation();
		String expectedLocation = "b4"; 
		
		Assert.assertNotEquals(expectedLocation, actualLocation); 
		
	}
	
	@Test
	public void getNameTest() {
		
		chip.setName("chip");
		String actualName = chip.getName();
		String expectedName = "chip"; 
		
		Assert.assertEquals(expectedName, actualName); 
	}
	
	@Test
	public void getQuantityTest() {
	
		int actualQuantity = chip.getQuantity();
		int expectedQuantity = 5; 
		
		Assert.assertEquals(expectedQuantity, actualQuantity); 
	}
	
	

}
