package uk.gov.dvla.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class VehicleEnquiryPage extends BasePage {

	@FindBy(how = How.XPATH, using = "//*[@id='Vrm']")
	private WebElement searchBox;

	@FindBy(how = How.NAME, using = "Continue")
	private WebElement continueBtn;
	
	@FindBy(
			how = How.XPATH, 
			using = "//span[@id='Vrm-error' and contains(text(), 'You must enter your registration number in a valid format')]"
	)
	private WebElement errorMsg;

	public VehicleEnquiryPage(WebDriver driver) {
		super(driver);
	}

	public void fillSearchBox(String registrationPlate) {
		this.fillTextBox(registrationPlate, searchBox, 10);
	}

	public InitialResultPage searchForResults() {
		this.click(continueBtn, 5);
		return PageFactory.initElements(driver, InitialResultPage.class);
	}

	public String getErrorFromSearch() {
		return this.waitForElementToAppear(errorMsg, 30).getText();
	}
}
