package com.macd.pageobjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.macd.base.BaseClass;
import com.macd.utilities.Log;

/**
 * This is the page object class for Cart page
 */
public class CartPage extends BaseClass{
	By clearAll_link = By.xpath("//a[contains(text(),'clear all')]");
	By OkButton = By.xpath("//button[contains(text(),'OK')]");
	By cartsection = By.xpath("//div[@class=\"d-flex details\"]");
	By plusbutton  = By.xpath("//button[@class=\"plus\"]");
	By errormessage = By.xpath("//div[@class=\"errorDetail\"]");
	
	public CartPage(WebDriver driver1) {
		this.driver = driver1;
		
	}
	// This method is used to clear all the selected items 
	public String clearSelectedItems() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(2000);
		//verifying the link is displayed on the cart page 
		Assert.assertEquals(true, driver.findElement(clearAll_link).isDisplayed());
		Log.info("Click on the Clear All link");
		// click on clear all link
		driver.findElement(clearAll_link).click();
		Thread.sleep(4000);
		// storing the message into the variable
		String message = driver.findElement(errormessage).getText();
		
		Thread.sleep(8000);
		//verifying the Ok Button is displayed on the Cart Page
		Assert.assertEquals(true, driver.findElement(OkButton).isDisplayed());
		//click on OK Button.
		Log.info("Click on the Ok Button");
		driver.findElement(OkButton).click();
		return message;
	}
	
	// This method verify the selected items on the Cart Page
	public String checkAllItems() {
		//verifying element is displayed on the home page 
		Assert.assertEquals(true, driver.findElement(cartsection).isDisplayed());
		Log.info("Verify that all of the selected items and customize items are displayed on the cart page. ");
		// checking and storing the items into the variable
		String itemDetails = driver.findElement(cartsection).getText();
		return itemDetails;
	}
	
	// This method is use to add the quantity.
	public void addItem() throws InterruptedException {
		try {
		//verifying element is displayed on the home page 
		Assert.assertEquals(true, driver.findElement(By.xpath("(//button[@class=\"plus\"])[1]")).isDisplayed());
		Log.info("Click on \"+\" button to add quentity");
		// click on + button. 
		driver.findElement(By.xpath("(//button[@class=\"plus\"])[1]")).click();
		Thread.sleep(3000);
		// checking the message 
		driver.findElement(By.id("message")).getText();
		}
		catch(Exception e) {
			// creating the object for Extent Report			
			ExtentTest TC_012 =  extent.createTest("Verifying the message when the user adds the quantity");
			TC_012.fail("The user is not receiving any message as expected");
			Assert.fail("The appliction is not throwing any message");
		}
	}
}