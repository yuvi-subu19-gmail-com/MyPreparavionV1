package com.automation.mypreparation.tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.automation.mypreparation.utiities.ConfigProp;

public class BaseTestClass {

	/*
	 * String UserName="yuvi.subu@yopmail.com"; String Password="Welcome@123";
	 * String URL="http://automationpractice.com/index.php";
	 */
    
	public static WebDriver driver;
	public static Logger logger;
	ConfigProp prop=new ConfigProp();

	@BeforeClass
	@Parameters("Browser")
	public void setup(String browser, ITestContext context) {
		
		logger = Logger.getLogger("MyPreparation");
		PropertyConfigurator.configure("Log4j.properties");
		
		if(browser.equalsIgnoreCase("chrome"))
		{
		logger.info("Chrome browser is requested");
		System.setProperty("webdriver.chrome.driver", prop.getChromeDriver());
		driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("ie"))
		{
			logger.info("IE browser is requested");
			System.out.println("IE is yet to setup. Please use Chrome browser until then");
		}
		context.setAttribute("webDriver", driver);
		
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		logger.info("Chrome is initialized");
		driver.get(prop.getURL());
		logger.info("URL is opened");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		logger.info("WebDriver is closed");
	}

}
