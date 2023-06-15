package testBase;
//methods in baseclass are reusable
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseclass {
public WebDriver driver;
public Logger logger;
	
	@BeforeClass
	public void setup()
	{
		logger=LogManager.getLogger(this.getClass());
		ChromeOptions options=new ChromeOptions();
    	options.addArguments("remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
	}
		
    @AfterClass
    public void teardown()
    {
    	driver.close();
    }
   
    public void captureScreen(WebDriver driver,String fname)throws IOException
    {
    	TakesScreenshot ts=(TakesScreenshot) driver;
    	File source=ts.getScreenshotAs(OutputType.FILE);
    	File target=new File(System.getProperty("user.dir")+"\\screenshots\\"+fname+".png");
    	FileUtils.copyFile(source,target);
    	
    	
    }
}
