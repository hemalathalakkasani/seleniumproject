package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class gomsapp {
	
	WebDriver driver;
	public gomsapp(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
    @FindBy(name="userName")
    WebElement username;
    @FindBy(name="password")
    WebElement password;
    @FindBy(xpath="//button[@type='submit']")
    WebElement button;
    @FindBy(xpath="//div[@id='content-wrapper']")
    WebElement logopg;
    @FindBy(xpath="//i[@class='fa fa-power-off']")
    WebElement logoutbutton;
    
    public void setusername(String uname)
    {
    	username.clear();
    	username.sendKeys(uname);
    }
    public void setpassword(String pwd)
    {
    	password.clear();
    	password.sendKeys(pwd);
    }
    public void onsubmit()
    
    {
    	button.click();
    }
    public boolean logopage()
    {
    	return logopg.isDisplayed();
    }
    public void logout()
    {
    	logoutbutton.click();
    }
}
