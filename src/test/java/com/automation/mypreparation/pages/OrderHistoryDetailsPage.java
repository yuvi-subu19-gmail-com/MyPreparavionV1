package com.automation.mypreparation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderHistoryDetailsPage  {
WebDriver driver;

@FindBy(xpath="//a[@title='Orders']")
WebElement menuOrderHistory;

	public OrderHistoryDetailsPage(WebDriver rdriver)
	{
		driver=rdriver;
		PageFactory.initElements(rdriver, this);
		
	}
	
	public void clickOrderHistoryMenu()
	{
		menuOrderHistory.click();
	}
	

}
