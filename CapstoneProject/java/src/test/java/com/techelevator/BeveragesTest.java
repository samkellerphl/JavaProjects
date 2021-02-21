package com.techelevator;

import java.math.BigDecimal;

import org.junit.*;

public class BeveragesTest {
	
	
	Beverages coke = new Beverages();
	
	
	@Test
	public void getSound() {
		
		String actualSound = coke.getSound(); 
		String expectedSound = "Glug Glug, Yum!"; 
		
		Assert.assertEquals(expectedSound, actualSound); 
		
	}
	
	@Test
	public void getSound_02() {
		
		String actualSound = coke.getSound(); 
		String expectedSound = "Glug, Yum!"; 
		
		Assert.assertNotEquals(expectedSound, actualSound); 
		
	}
	
	@Test
	public void getPriceTest() {
		
		coke.setPrice(BigDecimal.valueOf(2));
		BigDecimal actualPrice = coke.getPrice();
		
		BigDecimal expectedPrice = BigDecimal.valueOf(2); 
		
		Assert.assertEquals(expectedPrice, actualPrice); 
		
	}
	
	@Test
	public void getSlotLocationTest() {
		
		coke.setSlotLocation("a1");
		String actualLocation = coke.getSlotLocation();
		String expectedLocation = "b4"; 
		
		Assert.assertNotEquals(expectedLocation, actualLocation); 
		
	}
	
	@Test
	public void getNameTest() {
		
		coke.setName("coke");
		String actualName = coke.getName();
		String expectedName = "coke"; 
		
		Assert.assertEquals(expectedName, actualName); 
	}
	
	@Test
	public void getQuantityTest() {
	
		int actualQuantity = coke.getQuantity();
		int expectedQuantity = 4; 
		
		Assert.assertNotEquals(expectedQuantity, actualQuantity); 
	}
	
	

}
