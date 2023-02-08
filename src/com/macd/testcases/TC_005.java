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
 * This class is used to verifying the address displayed on the top of the home page after selecting  
 */
//Extending the BaseClass to inherit its properties and methods
public class TC_005 extends BaseClass{
	
	// Creating objects for the page object classes that are required. 
	HomePage homepage= new HomePage(driver);
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
	
	//After Method
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	//Test Method
	@Test (dataProvider = "Address", dataProviderClass = dataProvider.class)
	public void locationModuleTest(String address, String fooditem) throws InterruptedException {
		Log.startTestCase("locationTest");
		Thread.sleep(2000);
		
		// Calling the HomePage class method.
		homepage.clickonSelectNow();
		Thread.sleep(5000);
		
		// Calling the LocationPage class method.
		String actualMessage = locationpage.checkMessage(address);
		
		// Storing expectedMessage in a variable.
		String expectedMessage = "We do not serve this location as of now.";
		
		//verifying the test using the Assert class.
		Assert.assertEquals(actualMessage, expectedMessage);
		
		// Creating an object for the Extent Report.
		ExtentTest TC_005 =  extent.createTest("Verify the message when the user enters an address that is out of range.");
		if (actualMessage.equals(expectedMessage)) {
			TC_005.pass("The user is receiving the message as expected.");
		}
		else {
			TC_005.fail("The user is not receiving the message.");
		}
		
		// calling the HomePage class method.
		homepage.typeItemsInTextBox(fooditem);
		Thread.sleep(2000);
		
		// Calling the CustomizationPage class method.
		customizationpage.selectAddonItems();
		Thread.sleep(2000);
		
		// Calling the CustomizationPage class method.
		customizationpage.selectRodioButton();
		Thread.sleep(2000);
		
		// Calling the HomePage class method.
		homepage.clickonViewCartButton();
		Thread.sleep(2000);
		
		// Calling the CertPage class method.
		cartpage.clearSelectedItems();

	}

}
