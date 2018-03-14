package uk.gov.dvla.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import uk.gov.dvla.businessSpecificClasses.Car;

public class InitialResultPage extends BasePage{
	
	@FindBy(
			how=How.XPATH, 
			using="//summary[@role='button']/span[@class='summary' and contains(text(), 'Incorrect vehicle details?')]"
	)
	private WebElement pageHeader;
	
	@FindBy(how = How.XPATH, using = "//span[@class='reg-mark']")
	private WebElement vehicleReg;
	
	@FindBy(how = How.XPATH, using = "//li[@class='list-summary-item']/span[contains(text(), 'Make')]/following-sibling::span")
	private WebElement vehicleMake;
	
	@FindBy(how = How.XPATH, using = "//li[@class='list-summary-item']/span[contains(text(), 'Colour')]/following-sibling::span")
	private WebElement vehicleColour;	
	
	public InitialResultPage(WebDriver driver) {
		super(driver);
	}

	public Car getCarInfoFromResults() {
		this.waitForElementToAppear(pageHeader, 30);
		Car result = new Car(vehicleMake.getText(), vehicleColour.getText(), vehicleReg.getText().replaceAll("\\s", ""));
		return result;
	}
}
