package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.gomsapp;

import testBase.Baseclass;
import utilities.XLUtils;

public class TC_001_goms_loginlogout extends Baseclass{
	
	
	@Test(dataProvider="LoginData")
	public void test_goms_login(String url,String username,String password,String status) throws IOException 
	{
	if(status.equals("yes"))
	{
	 driver.get(url);
	 driver.manage().window().maximize();
	 logger.info("application launched");
	 gomsapp g=new gomsapp(driver);
	 g.setusername(username);
	 g.setpassword(password);
	 g.onsubmit();	
	 captureScreen(driver,"test_loginpage");
	 logger.info("entered credentials and submitted");
	 boolean targetpg=g.logopage();
	 {
		 
	 if(targetpg==true)
	 {
		 logger.info("login success");	
		 captureScreen(driver,"test_goms_login");
		 g.logout();		 	
		 Assert.assertTrue(true);
	 }else	 
	 {   
		
		 logger.info("invalid username");
		 logger.info("invalid credentials");
		 captureScreen(driver,"test_goms_login");
		 Assert.assertTrue(false);
		 
	 }	 
	 }
       
	}
	else
	{
		System.out.println("skipped");
		throw new SkipException("skipping goms application");
			
		}
	}
	
     @DataProvider(name="LoginData")
		public String [][] getData() throws IOException
		{
			String path=".\\testData\\logincredentials.xlsx";
			XLUtils xlutil=new XLUtils(path);
			int totalrows=xlutil.getRowCount("Sheet1");
			int totalcols=xlutil.getCellCount("Sheet1",1);
			String logindata[][]=new String[totalrows][totalcols];
			for(int i=1;i<=totalrows;i++)
			{
				for(int j=0;j<totalcols;j++)
				{
					logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
				}
			}
			return logindata;
		}


}

