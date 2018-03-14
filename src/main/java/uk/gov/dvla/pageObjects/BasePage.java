package uk.gov.dvla.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePage {
	public final WebDriver driver;
	public final WebDriverWait wait5;
	public final WebDriverWait wait30;
	
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait5 = new WebDriverWait(this.driver, 5);
		this.wait30 = new WebDriverWait(this.driver, 30);
		PageFactory.initElements(this.driver, this);
	}
	
	
   
    public WebElement waitForElementToAppear(WebElement locator, int waitInSeconds) {
	    	WebElement e;
	    	
	    	if (waitInSeconds <= 0) {
	    		throw new IllegalArgumentException("Click Accepts waitInSeconds arguments greater than 0");
	    	}
	    	
	    	if (waitInSeconds == 5) {
	    		e = this.wait5.until(ExpectedConditions.visibilityOf(locator));
	    	}
	    	else if (waitInSeconds == 30) {
	    		e = this.wait30.until(ExpectedConditions.visibilityOf(locator));
	    	}
	    	else {
	    		WebDriverWait wait = new WebDriverWait(this.driver, waitInSeconds);
	    		e = wait.until(ExpectedConditions.visibilityOf(locator));
	    	}
	    	return e;
    }
    
    protected void fillTextBox(String input, WebElement locator, int waitInSeconds) {
    		this.waitForElementToAppear(locator, waitInSeconds).sendKeys(input);
    }
    
    protected void click(WebElement locator, int waitInSeconds) throws IllegalArgumentException {
    		this.waitForElementToAppear(locator, waitInSeconds).click();  
    }
}
