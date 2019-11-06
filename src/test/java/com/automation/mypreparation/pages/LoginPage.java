
package com.automation.mypreparation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.apache.log4j.Logger;

public class LoginPage {
	
	WebDriver driver;
	
	@FindBy(className="login")
	@CacheLookup
	WebElement menuLogin;
	
	@FindBy(id="email")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy(id="passwd")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(id="SubmitLogin")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(className="logout")
	@CacheLookup
	WebElement btnLogout;
static Logger logger;
	
	
	
	public LoginPage(WebDriver driver2, Logger logger2) {
		logger=logger2;
		driver=driver2;
		PageFactory.initElements(driver2, this);
	}

	public void login(String UserName, String Password)
	{
		clickMenuLogin();
		setUsername(UserName);
		setPassword(Password);
		clickBtnLogin();
		verifyTitle();
		
	}
	
	public void clickMenuLogin()
	{
		menuLogin.click();
		logger.info("Login Menu item is clicked");
	}
	
	public void setUsername(String UserName)
	{
		txtEmail.sendKeys(UserName);
		logger.info("Email id is entered" + UserName);
	}

	public void setPassword(String Password)
	{
		txtPassword.sendKeys(Password);
		logger.info("Password id is entered" + Password);
	}
	
	public void clickBtnLogin()
	{
		btnLogin.click();
		logger.info("Login button is cicked");
	}
	
	public void clickBtnLogout()
	{
		btnLogout.click();
		logger.info("Sign out button is clicked");
	}
	
	public void verifyTitle()
	{
		Assert.assertEquals(driver.getTitle(), "My account - My Store");
		logger.info("Login is successful and navigated to account page");
		
	}
}