package com.techelevator;

import java.math.BigDecimal;

import org.junit.*;

public class CalculatorTest {
	
	Calculator testCalc = new Calculator(); 
	
	@Test
	public void makeChangeTest() {
		
		BigDecimal actualResult = testCalc.makeChange();
		BigDecimal expectedResult = BigDecimal.ZERO; 
		
		Assert.assertEquals(expectedResult, actualResult); 
		
	}
	
	@Test
	public void makeChangeTest2() {
		
		testCalc.setCurrentBalance(BigDecimal.ONE);
		BigDecimal actualResult = testCalc.makeChange();
		BigDecimal expectedResult = BigDecimal.TEN; 
		
		Assert.assertNotEquals(expectedResult, actualResult); 
		
	} 
	
	@Test
	public void makeChangeTest3() {
		
		testCalc.setCurrentBalance(BigDecimal.valueOf(1.35));
		BigDecimal actualResult = testCalc.makeChange();
		BigDecimal expectedResult = BigDecimal.TEN; 
		
		Assert.assertNotEquals(expectedResult, actualResult); 
		
	} 
	
	@Test
	public void makeChangeTest4() {
		
		testCalc.setCurrentBalance(BigDecimal.valueOf(1.40));
		BigDecimal actualResult = testCalc.makeChange();
		BigDecimal expectedResult = BigDecimal.TEN; 
		
		Assert.assertNotEquals(expectedResult, actualResult); 
		
	} 
	
	
	@Test
	public void acceptChangeTest() {
		
		boolean actualResult = testCalc.acceptMoney("2");

		Assert.assertTrue(actualResult);
	}
	
	@Test
	public void acceptChangeTest2() {
		
		boolean actualResult = testCalc.acceptMoney("6");

		Assert.assertFalse(actualResult);
	}
	
	@Test
	public void acceptChangeTest3() {
		
		boolean actualResult = testCalc.acceptMoney("1");

		Assert.assertTrue(actualResult);
	}
	
	@Test
	public void acceptChangeTest4() {
		
		boolean actualResult = testCalc.acceptMoney("5");

		Assert.assertTrue(actualResult);
	}
	
	@Test
	public void acceptChangeTest5() {
		
		boolean actualResult = testCalc.acceptMoney("10");

		Assert.assertTrue(actualResult);
	}

	@Test
	public void getCurrentBalanceTest() {
		
		testCalc.setCurrentBalance(BigDecimal.valueOf(1.00)); 
		BigDecimal actualBalance = testCalc.getCurrentBalance();		
		BigDecimal expectedBalance = new BigDecimal(1.00).setScale(2); 

		Assert.assertEquals(expectedBalance, actualBalance);
	}

}
