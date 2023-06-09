package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.gamaapp;
import testBase.Baseclass;
import utilities.XLUtils;

public class TC_002_gama_loginlogout extends Baseclass{
	
	
	@Test(dataProvider="LoginData")
	public void test_gama_login(String url,String username,String password,String status)throws IOException
	{
		if(status.equals("yes"))
		{
		driver.get(url);
		driver.manage().window().maximize();
		logger.info("application launched");
		gamaapp gp=new gamaapp(driver);
		gp.setusername(username);
		gp.setpassword(password);
		gp.onclick();
		boolean mainpg=gp.targetpg();		
		if(mainpg==true)
		{
			logger.info("login success");
			gp.logout();
			logger.info("application logout successfully");
			Assert.assertTrue(true);			
		}
				
		else
		{
			logger.info("invalid credentials");
			Assert.assertTrue(false);
		}
		}
		else
		{
			System.out.println("skipped");
			throw new SkipException("skipping gama application");
		}
		
	}
	 
	
    		
	
			
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\logincredentials.xlsx";
		XLUtils xlutil=new XLUtils(path);
		int totalrows=xlutil.getRowCount("Sheet2");
		int totalcols=xlutil.getCellCount("Sheet2",1);
		String logindata[][]=new String[totalrows][totalcols];
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcols;j++)
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet2", i, j);
			}
		}
		return logindata;
	}



}
