                //Gama Application

package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class gamaapp {
	
	WebDriver driver;
	public gamaapp(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
      @FindBy(name="username") 
      WebElement username;      
      @FindBy(name="password")
      WebElement password;
      @FindBy(xpath="//span[@class='ui-button-text ui-c']")
      WebElement button;
      @FindBy(xpath="//a[normalize-space()='Dashboard'][1]")
      WebElement dashbdpg;
      @FindBy(xpath="//i[@class='pi pi-power-off']")
      WebElement logoutbutton;
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
      
      
      public boolean targetpg()
      {
    	  return dashbdpg.isDisplayed();
    	  
      }
      public void logout()
      {
    	  logoutbutton.click();
      }
}
