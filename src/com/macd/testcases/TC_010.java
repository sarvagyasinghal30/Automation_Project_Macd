package com.macd.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.macd.base.BaseClass;
import com.macd.pageobjects.CartPage;
import com.macd.pageobjects.CustomizationPage;
import com.macd.pageobjects.HomePage;
import com.macd.pageobjects.LocationPage;
import com.macd.utilities.Log;


//Extending the BaseClass to inherit its properties and methods
public class TC_010 extends BaseClass{
	// Creating objects for the page object classes that are required.
	HomePage homepage= new HomePage(driver);
	LocationPage locationpage = new LocationPage(driver);
	CustomizationPage customizationpage = new CustomizationPage(driver);
	CartPage cartpage = new CartPage(driver);
	

	@Parameters("browser")
	//Before method
	@BeforeMethod
	public void setUp(String browser) {
		// Launching the app and setting implicit wait for the driver
		launchApp(browser);
	}
	
	//After Method 
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	//Test method
	@Test 
	public void cartModuleTest() throws InterruptedException {
		Log.startTestCase("Verifying added items on the cart page");
		// Calling clickonSelectNow method from homepage class.
		homepage.clickonSelectNow();
		Thread.sleep(6000);
		// Calling location method from location page class and passing address, building details
		locationpage.locationMethod("ANS Books, Kannappar Thidal", "121");
		Thread.sleep(6000);
		//Calling typeItemInTetxBox from home page class and passing food item
		homepage.typeItemsInTextBox("Burger");
		Thread.sleep(3000);
		//Calling method from customization page
		customizationpage.selectAddonItems();
		Thread.sleep(4000);
		//Calling method from customization page
		customizationpage.selectRodioButton();
		Thread.sleep(5000);
		//calling method from home page class 
		homepage.clickonViewCartButton();
		Thread.sleep(5000);
		//storing actual items name into the variable
		String actualResult = cartpage.checkAllItems();
		//storing expected items name into the variable
		String expectedResult = "McAloo Tikki Burger\n"
				+ "Coke,Extra Tomato Ketchup Sachet,Extra Cheese";
		//verifying the test 
		Assert.assertEquals(actualResult, expectedResult);
		// Creating an extent test to report the result of the verification
		ExtentTest TC_010 =  extent.createTest("Verifying the item and add-on items in the cart page");
		if (actualResult.equals(expectedResult)) {
			TC_010.pass("The items is displayed as expected.");
		}
		else {
			TC_010.fail("The items is not displayed.");
		}
		
	}

}
