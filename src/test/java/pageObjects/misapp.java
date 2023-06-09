package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class misapp {
	WebDriver driver;
	public misapp(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
    @FindBy(name="username")
    WebElement username;
    @FindBy(name="password")
    WebElement password;
    @FindBy(xpath="//button[@type='submit']")
    WebElement button;    
    @FindBy(xpath="//span[normalize-space()='Daily Generations']")
    WebElement mainpg; 
   
    public void setusername(String uname)
    {
    	username.sendKeys(uname);
    }
    public void setpassword(String pwd)
    {
    	password.sendKeys(pwd);
    }
    public void onclick()
    {
    	button.click();
    }
    public boolean submitpg()
    {
    	return mainpg.isDisplayed();
    }
   
}
