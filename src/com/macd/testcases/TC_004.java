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
 * This class is used to verify the error message for location module
 */
//Extending the BaseClass to inherit its properties and methods
public class TC_004 extends BaseClass {
	// Creating object of the page object classes 
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
		public void checkLocationModule() throws InterruptedException {
			Log.startTestCase("Checking the error message");
			// clicking on Select Now button
			homepage.clickonSelectNow();
			Thread.sleep(5000);
			// Entering invalid address
			locationpage.invalidAddress("@#%#");
			// Clicking on back icon
			locationpage.click_On_backIcon();
		}
}