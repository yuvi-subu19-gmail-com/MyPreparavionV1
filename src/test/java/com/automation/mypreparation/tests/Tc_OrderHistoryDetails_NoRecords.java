package com.automation.mypreparation.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.mypreparation.pages.LoginPage;
import com.automation.mypreparation.pages.OrderHistoryDetailsPage;
import com.automation.mypreparation.utiities.ExcelUtils;

public class Tc_OrderHistoryDetails_NoRecords extends BaseTestClass {
	
	 String Sheet="Sheet2";
     OrderHistoryDetailsPage ohdp;
     LoginPage lp;
	
	@Test(dataProvider="ohdp")
	public void verifyOrderHistoryDetais_NoRecords(String testCase)
	{
	 ohdp=new OrderHistoryDetailsPage(driver);
     lp=new LoginPage(driver,logger);
		lp.login(prop.getUserName(), prop.getPassword());
		ohdp.clickOrderHistoryMenu();
		Boolean bool=driver.getPageSource().contains("You have not placed any orders.");
		Assert.assertTrue(bool, "No Records found Warning displayed as expected");
		logger.info("No Records found Warning displayed as expected");
	}
	
	@DataProvider(name="ohdp")
	public String[][] getData() throws IOException
	{
		logger.info("Row count is" +ExcelUtils.getRowCount(prop.getTestDataPath(),Sheet));
		String[][] drivingTable=ExcelUtils.getExcelData(prop.getTestDataPath(),Sheet);
		return drivingTable;
		
	}

}
