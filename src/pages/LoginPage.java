package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
    
    @FindBy(id = "usernameInput-input")
    public WebElement email;
    
    @FindBy(name = "password")
    public WebElement pass;
    
    @FindBy(id = "signIn")
    public WebElement loginButton;
    
    @FindBy(id = "globalError")
    public WebElement globalError;
    
    @FindBy(className = "Error__text")
    public WebElement error;
    
	
	public void openBrowser() throws IOException {
		FileInputStream f = new FileInputStream("/Users/vatsalpatel/Desktop/Testing/prop.properties");
		Properties prop = new Properties();
		prop.load(f);
		
		String browser = prop.getProperty("browser");
		
		if (browser.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "/Users/vatsalpatel/Desktop/SeleniumJars/geckodriver");
			//ClassName ObjName = new ClassName();
			driver = new FirefoxDriver();
			} else if (browser.equals("Chrome")){
				System.setProperty("webdriver.chrome.driver", "/Users/vatsalpatel/Desktop/SeleniumJars/chromedriver");
			driver = new ChromeDriver();
			} else {
				System.setProperty("webdriver.safari.driver", "/Users/vatsalpatel/Desktop/SeleniumJars/safaridriver");
			driver = new SafariDriver();
			}
			
		PageFactory.initElements(driver, this);
		
		
	}
	
	public void openLoginPage() {
		driver.get("https://auth.scotiaonline.scotiabank.com/online?oauth_key=J4PpHCSEA64&oauth_key_signature=eyJraWQiOiJrUFVqdlNhT25GWUVDakpjMmV1MXJvNGxnb2VFeXJJb2tCbU1oX3BiZXNVIiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJyZWZlcmVyIjoiaHR0cHM6XC9cL3d3dy5zY290aWFiYW5rLmNvbVwvIiwib2F1dGhfa2V5IjoiSjRQcEhDU0VBNjQiLCJjb25zZW50X3JlcXVpcmVkIjpmYWxzZSwicmVkaXJlY3RfdXJpIjoiaHR0cHM6XC9cL3d3dy5zY290aWFvbmxpbmUuc2NvdGlhYmFuay5jb21cL29ubGluZVwvbGFuZGluZ1wvb2F1dGhsYW5kaW5nLmJucyIsImV4cCI6MTY0Mjk3MjAyNSwiaWF0IjoxNjQyOTcwODI1LCJqdGkiOiJhZTkxYjRjOS01OGZhLTQ3YWMtYjgwYS02YTM2MjhkOGEyZGUiLCJjbGllbnRfaWQiOiI4ZWU5MGMzOS0xYzUyLTRmZjQtOGFlNi1hN2I1NGM1Mzk5MzMiLCJjbGllbnRfbWV0YWRhdGEiOnsiQ2hhbm5lbElEIjoiU09MIiwiQXBwbGljYXRpb25Db2RlIjoiSDcifSwiaXNzdWVyIjoiaHR0cHM6XC9cL3Bhc3Nwb3J0LnNjb3RpYWJhbmsuY29tIn0.AoY6hoCqgvZa1af4-xVQn0zm6mJv6CoPTh19RwvBjqyEdV_yFBBConQpAmwNYGCVaS1-cAA6qTDabnGeT8HquApyd5EnMsGVHOlYXHZ-CtdbxEXCKh_pHO8tU9QURAxKuezdE42bv3iiJ-IPJ9YDmWWP0-e6U_iXrJKBU6YjzGdxdCk_hQ3W7ENuUIGGory7oNZQYLOMYvEyt7nCMgp76VoBu0aV4EBFsgF2rwJG8qgtA_IPr0bzihkKQ4IhRNUS_dElweN-N2VHJA1bUZLlhpkKSN6OJnD-KUmVkHQaFTfybt5K4cxCElmpsTL_uwqewEQBf2mw7szKc2QyBPIh6g&preferred_environment=");
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));  //Max time. Not usually fixed all the time 
	    
		
	}
	
	public void closeBrowser() {
		driver.quit();
	}
	
	public void login(String username, String password) throws InterruptedException {
		email.sendKeys(username);
		pass.sendKeys(password);
		loginButton.click();
	
		Thread.sleep(2000);
	}
	
	public String readGlobalError() {
		String actualErrMsg = globalError.getText();
		System.out.println(actualErrMsg);
		return actualErrMsg;
		
	}
	
	public String readError() {
		String actualErrorMsg = error.getText();
		 System.out.println(actualErrorMsg);
		return actualErrorMsg;
	}

}
