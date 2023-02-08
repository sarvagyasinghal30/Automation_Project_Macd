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
import com.macd.utilities.dataProvider;

/**
 * This class is used to verify the message
 */

//Extending the BaseClass to inherit its properties and methods
public class TC_006 extends BaseClass{
	
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
	
	//Test Method
	@Test (dataProvider = "addresstest", dataProviderClass = dataProvider.class)
    public void locationModuleTest(String address, String buildDetails, String foodItems) throws Exception{
		Log.startTestCase("check the address's availability on the home page");
		Thread.sleep(2000);
		
		// Invoking the HomePage class method.
		homepage.clickonSelectNow();
		Thread.sleep(5000);
		
		// Invoking the LocationPage class method.
		locationpage.locationMethod(address, buildDetails);
		Thread.sleep(4000);
		
		// Invoking the HomePage class method.
		boolean result = homepage.checkAddress();
		
		//verifying the test using the Assert class.
		Assert.assertTrue(result);
		
		// Creating an object for the Extent Report.
		ExtentTest TC_006 =  extent.createTest("Verify that the address is displayed at the top of the home page after selecting it.");
		if (result) {
			TC_006.pass("As expected, the address appears at the top of the home page. ");
		}
		else {
			TC_006.fail("The address is not displaying at the top of the home page.");
		}
		Thread.sleep(2000);
		
		// Invoking the HomePage class method.
		homepage.typeItemsInTextBox(foodItems);
		Thread.sleep(2000);
		
		// Invoking the CustomizationPage class method.
		customizationpage.selectAddonItems();
		Thread.sleep(2000);
		
		// Invoking the CustomizationPage class method.
		customizationpage.selectRodioButton();
		Thread.sleep(2000);
		
		// Invoking the HomePage class method.
		homepage.clickonViewCartButton();
		Thread.sleep(2000);
		
		// Invoking the CartPage class method.
		cartpage.clearSelectedItems();

	}

}
