package com.macd.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import com.macd.base.BaseClass;
import com.macd.utilities.Log;
/**
 * This is the page object class for HomePage
 */
public class HomePage extends BaseClass {
	private By select_Now_Btn = By.xpath("(//button[@class=\"btn primary-btn\"])[1]");
	private By checkAddress = By.xpath("//app-header/div[1]/div[1]/div[1]/div[1]/div[3]/app-nudge[1]/div[1]");
	private By play_Store_Btn = By.xpath("//img[@class = \"w-50 h-50 pl-2\"]");
	private By view_Cart_Btn = By.xpath("//button[@class = \"btn primary-btn flex-grow-1\"]");
	private By veg_Only_Option = By.xpath("(//span[@class = \"slider round \"])[2]");
	private By addbutton = By.cssSelector("app-menu-item:nth-child(1) .d-lg-block .btn");
	private By search_Items_TextBox = By.id("menu-search");
	private By errormessage = By.xpath("//div[@class=\"no-results\"]");
	private By Itemimage = By.xpath("(//img[@class=\"menu-img\"])[1]");
	boolean itemimage;
	public HomePage(WebDriver driver1)
	{
		this.driver = driver1;
	}
	
	//This method is used to click on select Now button
	public void clickonSelectNow() {
		//verifying title
		assertEquals(driver.getTitle(), "McDonald's - Order McDonald's Burger & Wraps online|McDelivery");
		//Verifying if element is displayed
		Assert.assertTrue(driver.findElement(select_Now_Btn).isDisplayed(), "the element is not displayed");
		// click on the select Now button
		Log.info("Click on the \"Select Now\" button to select the address");
		driver.findElement(select_Now_Btn).click();
	}
	
	//This method is used to verify if the address is selected
	public boolean checkAddress() {
		Log.info("Verify the address is displayed at the top of the home page.");
		// checking the address display on the home page after selecting 
		driver.findElement(By.xpath("//app-header/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]")).click();
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		boolean isaddressdisplay = driver.findElement(checkAddress).isDisplayed();
		return isaddressdisplay;
	}
	
	//This method is used to verifying the item
	public boolean typeItemsInTextBox(String item) throws InterruptedException {
		Thread.sleep(5000);
		//verifying the text box is displayed on the home page 
		Assert.assertTrue(driver.findElement(search_Items_TextBox).isDisplayed(), "the text box is not displayed");
		// entering the valid food item into the text box
		Log.info("Enter a valid food item into the text box, which is available on the home page.");
		assertTrue(driver.findElement(search_Items_TextBox).isDisplayed(), "Text box is displayed");
		driver.findElement(search_Items_TextBox).sendKeys(item);
		Thread.sleep(2000);
		//verifying the \"veg-Only\" button is displayed on the home page 
		Assert.assertTrue(driver.findElement(veg_Only_Option).isDisplayed(), "the button is not displayed");
		// click on the veg only button
		Log.info("Select the \" Veg-Only\" button");
		driver.findElement(veg_Only_Option).click();
		Thread.sleep(2000);
		// checking the item 
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		boolean itemImage = driver.findElement(Itemimage).isDisplayed();
		Thread.sleep(5000);
		//verifying the \"Add\" button is displayed on the home page 
		Assert.assertTrue(driver.findElement(addbutton).isDisplayed(), "the \"Add\" button is not displayed");
		//click on the add to select the food item.
		Log.info("Click on the \"Add\" button in order to select the item for order.");
		driver.findElement(addbutton).click();
		Thread.sleep(2000);
		return itemImage;
	}

	//This method is used to click on the View Cart button
	public void clickonViewCartButton() {
		//verifying the \" View Cart\" button is displayed on the home page 
		Assert.assertTrue(driver.findElement(view_Cart_Btn).isDisplayed(), "the \" View Cart\" button is not displayed");
		//click on the view cart button
		Log.info("Click on the \"View Cart\" button.");
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.findElement(view_Cart_Btn).click();
		
	}
	//This method is used to click on the play store button
	public void clickonPlayStoreButton() {
		Log.info("Click on the \"Play Store\" button.");
		driver.findElement(play_Store_Btn).click();
	}
	
	// This method will check the error message and return the message.
	public String checkerrormessage(String item) throws InterruptedException {
		//verifying the text box is displayed on the home page 
		Assert.assertTrue(driver.findElement(search_Items_TextBox).isDisplayed(), "the text box is not displayed");
		Thread.sleep(3000);
		Log.info("Enter an invalid food item into the text box, which is available on the home page.");
		// entering the invalid food item into the text box
		driver.findElement(search_Items_TextBox).sendKeys(item);
		Thread.sleep(5000);
		// storing the error message into the variable
		Log.info("Store the error message in a variable");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String errorMe = driver.findElement(errormessage).getText();
		//returing the error message
		return errorMe;
	}
	
	//This method is used to verifying the item
	public boolean verifyItemPresent(String item) throws InterruptedException {
		try {
		Thread.sleep(3000);
		Log.info("Enter only <=3 characters of the food item into the text box, which is available on the home page.");
		// entering only 3 or less then 3 character of food item into the text box.
		driver.findElement(search_Items_TextBox).sendKeys(item);
		Thread.sleep(2000);
		// checking the item image
		itemimage = driver.findElement(Itemimage).isDisplayed();
		
	}
	catch(NoSuchElementException e){
		// creating an object for Extent report
		ExtentTest TC_003 =  extent.createTest("Verify the item when the user only writes <=3 characters into the text box.");
		TC_003.fail("The application is not displaying any items as expected.");
		Assert.fail("Element is not present");
	}
		return itemimage;
	}
}
