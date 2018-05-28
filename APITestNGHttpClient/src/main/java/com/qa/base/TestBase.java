package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	//Defining expected response status codes
	public int Expec_statusresponse_200 = 200;
	public int Expec_statusresponse_201 = 201;
	public int Expec_statusresponse_400 = 400;
	public int Expec_statusresponse_401 = 401;
		
	public Properties prop;
	
	public TestBase()
	{
	
	try 
	{
		prop = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/config.properties");
		prop.load(ip);
	}
	catch (FileNotFoundException e) 
	
	{
		e.printStackTrace();
		
	}
	catch (IOException e)
	{
	   e.printStackTrace();
	}
}
}

