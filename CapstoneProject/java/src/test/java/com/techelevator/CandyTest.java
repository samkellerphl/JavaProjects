package com.techelevator;

import java.math.BigDecimal;

import org.junit.*;

public class CandyTest {
	
	
	Candy candy = new Candy();
	
	
	@Test
	public void getSound() {
		
		String actualSound = candy.getSound(); 
		String expectedSound = "Munch Munch, Yum!"; 
		
		Assert.assertEquals(expectedSound, actualSound); 
		
	}
	
	@Test
	public void getSound_02() {
		
		String actualSound = candy.getSound(); 
		String expectedSound = "Glug, Yum!"; 
		
		Assert.assertNotEquals(expectedSound, actualSound); 
		
	}
	
	@Test
	public void getPriceTest() {
		
		candy.setPrice(BigDecimal.valueOf(5));
		BigDecimal actualPrice = candy.getPrice();
		
		BigDecimal expectedPrice = BigDecimal.valueOf(5); 
		
		Assert.assertEquals(expectedPrice, actualPrice); 
		
	}
	
	@Test
	public void getSlotLocationTest() {
		
		candy.setSlotLocation("c3");
		String actualLocation = candy.getSlotLocation();
		String expectedLocation = "b2"; 
		
		Assert.assertNotEquals(expectedLocation, actualLocation); 
		
	}
	
	@Test
	public void getNameTest() {
		
		candy.setName("candy");
		String actualName = candy.getName();
		String expectedName = "cady"; 
		
		Assert.assertNotEquals(expectedName, actualName); 
	}
	
	@Test
	public void getQuantityTest() {
	
		int actualQuantity = candy.getQuantity();
		int expectedQuantity = 5; 
		
		Assert.assertEquals(expectedQuantity, actualQuantity); 
	}
	
	

}
