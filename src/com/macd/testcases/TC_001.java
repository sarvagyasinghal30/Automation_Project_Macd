package com.macd.testcases;
import static org.testng.Assert.assertEquals;

//Importing required packages for the test case
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
import com.macd.utilities.dataProvider;

/**
 * This class is used to verify the presence of item 
 */

//Extending the BaseClass to inherit its properties and methods
public class TC_001 extends BaseClass  {

	// Creating object of the page object classes to use their methods in the test case
	HomePage homepage = new HomePage(driver);
	LocationPage locationpage = new LocationPage(driver);
	CustomizationPage customizationpage = new CustomizationPage(driver);
	CartPage cartpage = new CartPage(driver);
	
	
	@Parameters("browser")
	//Before method
	@BeforeMethod
	public void setUp(String browser) {
		// Launching the application
		launchApp(browser);
		
	}
	
	// Method to quit the driver after the test case execution is complete
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	// Test method for verifying the presence of the item
	@Test (description = "Verify the presence of the item.", dataProvider = "addresstest", dataProviderClass = dataProvider.class)
	public void verify_Item_Present(String address, String build_Details, String foodItem) throws InterruptedException {
		// Logging the start of the test case
		Log.startTestCase("verify_Item_Present");
		
		// Calling clickonSelectNow method from homepage class.
		homepage.clickonSelectNow();
		Thread.sleep(6000);
		
		// Calling location method from location page class and passing address, building details
		locationpage.locationMethod(address, build_Details);
		
		Thread.sleep(10000);
		// Searching for the food item and verifying if it is present
		boolean result = homepage.typeItemsInTextBox(foodItem);
		Assert.assertTrue(result);
		// Creating an extent test to report the result of the verification
		ExtentTest TC_001 =  extent.createTest("Verifying the items' result after entering a valid food item");
		// If the item is present, reporting a pass, else reporting a fail
		if (result) {
			TC_001.pass("The valid food item is displayed as expected.");
		}
		else {
			TC_001.fail("The valid food item is not displayed");
		}
		Thread.sleep(2000);
		// Selecting the add-ons, radio button and navigating to the cart page
		customizationpage.selectAddonItems();
		Thread.sleep(4000);
		customizationpage.selectRodioButton();
		Thread.sleep(4000);
		homepage.clickonViewCartButton();
		Thread.sleep(4000);
		// Clearing the selected items from the cart
		cartpage.clearSelectedItems();
	}
	

}