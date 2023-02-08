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
public class TC_007 extends BaseClass{
	
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
	@Test (dataProvider = "foodItem", dataProviderClass = dataProvider.class)
	public void locationModuleTest(String foodItem) throws InterruptedException {
		Log.startTestCase("Location Test");
		Thread.sleep(5000);
		
		// Invoking the HomePage class method.
		homepage.clickonSelectNow();
		Thread.sleep(5000);
		
		// Invoking the LocationPage class method.
		String actualMessage = locationpage.current_Location();
		String expectedMessage = "We do not serve this location as of now.";
		
		//verifying the test using the Assert class.
		Assert.assertEquals(actualMessage, expectedMessage);
		
		// Creating an object for the Extent Report.
		ExtentTest TC_007 =  extent.createTest("Select the addres using use current location option");
		if (actualMessage.equals(expectedMessage)) {
			TC_007.pass("The user is receiving the message as expected.");
		}
		else {
			TC_007.fail("The user is not receiving the message.");
		}
		
		// Invoking the HomePage class method.
		homepage.typeItemsInTextBox(foodItem);
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
