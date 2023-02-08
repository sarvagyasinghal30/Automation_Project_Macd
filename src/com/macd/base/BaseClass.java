/**
 *  This is the BaseClass for the Macd project. 
 *  It sets up the ExtentReports and launches the browser
 */
package com.macd.base;
//Import necessary libraries
import java.util.concurrent.TimeUnit;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

//Declare the class and variables
public class BaseClass {
	// Base URL for the project
	public static String baseUrl= "https://www.mcdelivery.co.in/home";
	public static WebDriver driver;
	
	// ExtentReports for reporting
	public static ExtentReports extent;
	public static ExtentHtmlReporter spark;
	@BeforeSuite
	public void beforeSuite() throws Exception {
		extent=new ExtentReports();
        spark=new ExtentHtmlReporter(System.getProperty("user.dir") +"\\ExtentReport\\Macd_project.html");
        extent.attachReporter(spark);    
		DOMConfigurator.configure("log4j.xml");
	}

	// Launch the ChromeDriver and maximize the window
	public void launchApp(String browser) {
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"//Drivers//chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") +"//Drivers//msedgedriver.exe");
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		
		// Load the base URL
		driver.get(baseUrl);
		
		// Set timeouts for page load and implicit wait
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	// Flush the ExtentReports after the suite has finished
	@AfterSuite
	public void afterSuite() {
		extent.flush();
	}
	
}