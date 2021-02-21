package com.techelevator;

import java.math.BigDecimal;

import org.junit.*;

public class GumTest {
	
	
	Gum gum = new Gum();
	
	
	@Test
	public void getSound() {
		
		String actualSound = gum.getSound(); 
		String expectedSound = "Chew Chew, Yum!"; 
		
		Assert.assertEquals(expectedSound, actualSound); 
		
	}
	
	@Test
	public void getSound_02() {
		
		String actualSound = gum.getSound(); 
		String expectedSound = "d;alksjdflajs Yum!"; 
		
		Assert.assertNotEquals(expectedSound, actualSound); 
		
	}
	
	@Test
	public void getPriceTest() {
		
		gum.setPrice(BigDecimal.valueOf(1));
		BigDecimal actualPrice = gum.getPrice();
		
		BigDecimal expectedPrice = BigDecimal.valueOf(2); 
		
		Assert.assertNotEquals(expectedPrice, actualPrice); 
		
	}
	
	@Test
	public void getSlotLocationTest() {
		
		gum.setSlotLocation("c3");
		String actualLocation = gum.getSlotLocation();
		String expectedLocation = "c3"; 
		
		Assert.assertEquals(expectedLocation, actualLocation); 
		
	}
	
	@Test
	public void getNameTest() {
		
		gum.setName("gum");
		String actualName = gum.getName();
		String expectedName = "guM"; 
		
		Assert.assertNotEquals(expectedName, actualName); 
	}
	
	@Test
	public void getQuantityTest() {
	
		int actualQuantity = gum.getQuantity();
		int expectedQuantity = 2; 
		
		Assert.assertNotEquals(expectedQuantity, actualQuantity); 
	}
	
	

}
