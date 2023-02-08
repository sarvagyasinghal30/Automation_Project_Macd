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
 * This class is used to verifying the adding of add-on items.  
 */

//Extending the BaseClass to inherit its properties and methods
public class TC_008 extends BaseClass{
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
		//closing the browser
		driver.quit();
	}
	
	//Test Method
	@Test( dataProvider = "addresstest" , dataProviderClass = dataProvider.class)
	public void customizationModuleTest(String address, String build_Details, String foodItem) throws InterruptedException {
		Log.startTestCase("Customization Test");
		homepage.clickonSelectNow();
		Thread.sleep(4000);
		locationpage.locationMethod(address, build_Details);
		Thread.sleep(6000);
		//Calling method from page objects class
		homepage.typeItemsInTextBox(foodItem);
		Thread.sleep(3000);
		String actualResult = customizationpage.selectAddonItems();
		String expectedResult = "#ffbc0f";
		Thread.sleep(4000);
		// Verifying the actual and expected result using Assert.assertEquals 
		//  and logging the result in extent reports
		Assert.assertEquals(actualResult, expectedResult);
		ExtentTest TC_008 =  extent.createTest("verify item is added");
		if (actualResult.equals(expectedResult)) {
			TC_008.pass("The item is added as expected");
		}
		else {
			TC_008.fail("The item is not added.");
		}
		//calling method from Customization page class 
		customizationpage.selectRodioButton();
		Thread.sleep(5000);
		//calling method from home page class 
		homepage.clickonViewCartButton();
		Thread.sleep(2000);
		//calling method from cart page class 
		cartpage.clearSelectedItems();
		
	}

}
