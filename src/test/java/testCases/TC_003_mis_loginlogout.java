package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.misapp;
import testBase.Baseclass;
import utilities.XLUtils;

public class TC_003_mis_loginlogout extends Baseclass{
	
	@Test(dataProvider="LoginData")
	public void test_mis_login(String url,String username,String password,String status) throws IOException, InterruptedException
	{
	 if(status.equals("yes"))
	 {
	 driver.get(url);
     driver.manage().window().maximize();
     logger.info("application launched");
     misapp gp=new misapp(driver);
     gp.setusername(username);
     gp.setpassword(password);
     gp.onclick();     
     boolean targetpg=gp.submitpg();
     if(targetpg==true)
     {
    	 logger.info("login success");       	     	 
    	 Assert.assertTrue(true);
     }else
     {
    	 logger.error("logincredentials failed");
    	 Assert.assertTrue(false);
     }
     }
	 else
	 {
		 logger.info("skipped mis");
		throw new SkipException("skipping mis application");
	 }
}    
	
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\logincredentials.xlsx";
		XLUtils xlutil=new XLUtils(path);
		int totalrows=xlutil.getRowCount("Sheet3");
		int totalcols=xlutil.getCellCount("Sheet3",1);
		String logindata[][]=new String[totalrows][totalcols];
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcols;j++)
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet3", i, j);
			}
		}
		return logindata;
	}


}

