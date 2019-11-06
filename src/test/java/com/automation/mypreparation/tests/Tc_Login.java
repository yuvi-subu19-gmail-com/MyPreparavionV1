package com.automation.mypreparation.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.mypreparation.pages.LoginPage;
import com.automation.mypreparation.utiities.ConfigProp;
import com.automation.mypreparation.utiities.ExcelUtils;

public class Tc_Login extends BaseTestClass {

	LoginPage lp;
	@Test(dataProvider="loginData")
	public void loginTest(String UserName, String Password) {
		lp = new LoginPage(driver,logger);
	/*	lp.clickMenuLogin();
		logger.info("Login Menu item is clicked");
		lp.setUsername(UserName);
		logger.info("Email id is entered" + UserName);
		lp.setPassword(Password);
		logger.info("Password id is entered" + Password);
		lp.clickBtnLogin();
		logger.info("Login button is cicked");
		lp.verifyTitle();
		logger.info("Login is successful and navigated to account page");
		lp.clickBtnLogout();
		logger.info("Sign out button is clicked"); */
		lp.login(UserName, Password);
		lp.clickBtnLogout();
		
	}
	
	@DataProvider(name="loginData")
	public String[][] getLoginData() throws IOException
	{
		logger.info("Row count is" +ExcelUtils.getRowCount(prop.getTestDataPath(),prop.getLoginTestSheet()));
		String[][] drivingTable=ExcelUtils.getExcelData(prop.getTestDataPath(),prop.getLoginTestSheet());
		return drivingTable;
	}
	
	
	
	
	

}
