package com.macd.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.macd.base.BaseClass;
import com.macd.pageobjects.HomePage;
import com.macd.pageobjects.LocationPage;
import com.macd.utilities.Log;


/**
 * This class is used to verify the presence of item, when user enter only less then 4 characters 
 */
//Extending the BaseClass to inherit its properties and methods
public class TC_003 extends BaseClass  {
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
	@Test
	public void verify_Item_Present() throws InterruptedException {
		Log.startTestCase("Verify_Item_present");
		Thread.sleep(2000);
		
		// Calling the HomePage class method.
		homepage.clickonSelectNow();
		Thread.sleep(6000);
		
		// Calling the LocationPage class method.
		locationpage.locationMethod("ANS Books, Kannappar Thidal", "121");
		Thread.sleep(4000);
		
		// Calling the HomePage class method.
		homepage.verifyItemPresent("Tea");
	}
	

}