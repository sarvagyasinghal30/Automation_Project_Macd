package com.macd.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.macd.base.BaseClass;
import com.macd.pageobjects.HomePage;
import com.macd.pageobjects.LocationPage;
import com.macd.utilities.Log;
import com.macd.utilities.dataProvider;

/**
 * This class is used to verify the error message, after enter invalid item 
 */

//Extending the BaseClass to inherit its properties and methods
public class TC_002 extends BaseClass{
	
	// Creating objects for the page object classes that are required. 
	HomePage homepage = new HomePage(driver);
	LocationPage locationpage = new LocationPage(driver);
	
	
	@Parameters("browser")
	//Before method
	@BeforeMethod
	public void setUp(String browser) {
		// Launching the application
		launchApp(browser);
	}
	
	//After Method 
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	//Test Method
	@Test(dataProvider = "invaliditem", dataProviderClass = dataProvider.class)
	public void verify_Error_Message(String address, String build_details, String invaliditem) throws InterruptedException {
		Log.startTestCase("Verify_Error_Message");
		Thread.sleep(2000);
		
		// calling the HomePage class method.
		homepage.clickonSelectNow();
		Thread.sleep(6000);
		
		// Calling the LocationPage class method.
		locationpage.locationMethod(address, build_details);
		Thread.sleep(5000);
		
		//Get the error message
		String actualMessage = homepage.checkerrormessage(invaliditem);
		
		// storing the expected message in a variable.
		String expectedMessage = "No results found. Check your spelling or try something different.";
		
		// verifying the test using the Assert class.
		Assert.assertEquals(actualMessage , expectedMessage);
		
		// Creating an object for the Extent Report.
		ExtentTest TC_002 =  extent.createTest("Verifying the error message after entering an invalid item into the text box.");
		
		//Checking the pass/fail status of the test case
		if (actualMessage.equals(expectedMessage)) {
			TC_002.pass("The user is receiving the error message as expected.");
		}
		else {
			TC_002.fail("The user is not receiving the error message");
		}
		
	}
	

}
