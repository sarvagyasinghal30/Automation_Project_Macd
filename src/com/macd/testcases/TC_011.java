/**
 * 
 */
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
//Extending the BaseClass to inherit its properties and methods
public class TC_011 extends BaseClass{
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
	@Test ( dataProvider = "addresstest" , dataProviderClass = dataProvider.class)
	public void cartModuleTest(String address, String build_Details, String foodItem) throws InterruptedException {
		Log.startTestCase("Verifying the error message ");
		// Calling clickonSelectNow method from homepage class.
		homepage.clickonSelectNow();
		Thread.sleep(2000);
		// Calling location method from location page class and passing address, building details
		locationpage.locationMethod(address, build_Details);
		Thread.sleep(6000);
		//Calling typeItemInTetxBox from home page class and passing food item
		homepage.typeItemsInTextBox(foodItem);
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
		//storing actual message into the variable
		String actualResult = cartpage.clearSelectedItems();
		//storing expected message into the variable
		String expectedResult = "Are you sure you want to remove all items?";
		//verifying the test
		Assert.assertEquals(actualResult,expectedResult);
		// Creating an extent test to report the result of the verification
		ExtentTest TC_011 =  extent.createTest("Verifying the message after clicking on \"clear All\" link");
		if (actualResult.equals(expectedResult)) {
			TC_011.pass("The user is receiving the message as expected.");
		}
		else {
			TC_011.fail("The user is not receiving the message.");
		}
		
	}

}
