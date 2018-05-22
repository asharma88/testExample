package uk.gov.dvla.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class LandingPage extends BasePage {

	@FindBy(how = How.XPATH, using = "//p[@id='get-started']/a[@class='gem-c-button gem-c-button--start']")
	private WebElement getStarted;
	
	public LandingPage(WebDriver driver) {
		super(driver);
	}
	
	public void navigateTo(String url) {
		this.driver.get(url);
	}
	
	public VehicleEnquiryPage clickOnGetStarted() {
		this.click(getStarted, 30);
		return PageFactory.initElements(this.driver, VehicleEnquiryPage.class);
	}
	
	
}
