/**
 * 
 */
package com.macd.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.macd.base.BaseClass;
import com.macd.pageobjects.CartPage;
import com.macd.pageobjects.CustomizationPage;
import com.macd.pageobjects.HomePage;
import com.macd.pageobjects.LocationPage;
import com.macd.utilities.Log;
import com.macd.utilities.dataProvider;

public class TC_013 extends BaseClass{
	
	// Creating objects for the page object classes that are required. 
		HomePage homepage = new HomePage(driver);
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
		public void check_error_message(String address, String build_Details, String foodItem) throws InterruptedException {
			Log.startTestCase("checking the message");
			
			// Invoking the HomePage class method.
			homepage.clickonSelectNow();
			Thread.sleep(6000);
			
			// Invoking the LocationPage class method.
			locationpage.locationMethod(address, build_Details);
			Thread.sleep(6000);
			
			// Invoking the HomePage class method.
			homepage.typeItemsInTextBox(foodItem);
			
					
			// Invoking the CustomizationPage class method.
			customizationpage.selectAddonItems();
			Thread.sleep(4000);
			
			// Invoking the CustomizationPage class method.
			customizationpage.removemethod();;
			Thread.sleep(4000);
			
			// Invoking the HomePage class method.
			homepage.clickonViewCartButton();
			Thread.sleep(4000);
			
			// Invoking the CartPage class method.
			cartpage.clearSelectedItems();
		}

}
