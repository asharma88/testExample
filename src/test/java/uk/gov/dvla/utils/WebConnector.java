package uk.gov.dvla.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class WebConnector {
	static WebDriver driver = null;
	
	// default constructor
	private WebConnector(){
	}
	
	public static WebDriver getBrowser(String bType){
		if(bType.equals("Mozilla"))
			driver = new FirefoxDriver();
		else if(bType.equals("Chrome")){
		 System.setProperty("user.dir", System.getProperty("user.dir")+"//chromedriver//chromedriver.exe");
		    driver = new ChromeDriver();   
		}else if(bType.equals("IE")){
			// set path to exe
			driver= new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return getBrowser(bType);
	}
	
}
