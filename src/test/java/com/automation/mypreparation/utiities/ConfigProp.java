package com.automation.mypreparation.utiities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigProp {
	
	static Properties prop;
	FileInputStream fis;
	
	public ConfigProp()
	{
		prop=new Properties();
		try
		{
		fis=new FileInputStream("./Configuration/config.properties");
		prop.load(fis);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	public static String getUserName()
	{
		return prop.getProperty("UserName");
	}
	
	public static String getPassword()
	{
		return prop.getProperty("Password");
	}
	
	public static String getURL()
	{
		return prop.getProperty("URL");
	}
	
	public static String getChromeDriver()
	{
		return prop.getProperty("ChromeDriver");
	}

	public static String getTestDataPath()
	{
		return prop.getProperty("TestDataPath");
	}
	
	public static String getLoginTestSheet()
	{
		return prop.getProperty("LoginTestSheet");
	}
}


