package uk.gov.dvla.getvehicleinfo;

import java.util.HashMap;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uk.gov.dvla.businessSpecificClasses.Car;
import uk.gov.dvla.pageObjects.InitialResultPage;
import uk.gov.dvla.pageObjects.LandingPage;
import uk.gov.dvla.pageObjects.VehicleEnquiryPage;
import uk.gov.dvla.utils.ExcelReader;
import uk.gov.dvla.utils.FileDirParseService;
import uk.gov.dvla.utils.LogHelper;
import uk.gov.dvla.utils.TestFileObject;

public class VehicleInfoTests {
	
	private WebDriver driver;
	private LandingPage lp;
	private VehicleEnquiryPage vp;
	private InitialResultPage ip;
	private HashMap<String, Car> expectedResults = new HashMap<String, Car>();
	final String DATA_SHEET = "testdata";
	
	@Before
    public void beforeScenario(Scenario scenario){
		this.driver = new FirefoxDriver();
		String testDataPath = System.getProperty("user.dir")+"/src/test/resources/uk/gov/dvla/getvehicleinfo/";
		HashMap<String, TestFileObject> fileMap = FileDirParseService.getFileListMap(
				testDataPath, 
				new String[] {"csv", "xls", "xlsx"}, 
				new String[] {
						"application/vnd.ms-excel",
						"text/csv"
						}
		);
		
		for (String fileName: fileMap.keySet()) {
			if (fileMap.get(fileName).getExtension().equals("xls")|fileMap.get(fileName).getExtension().equals("xlsx")) {
				ExcelReader er = new ExcelReader(fileMap.get(fileName).getAbsolutePath());
				int count = er.getRowCount(this.DATA_SHEET);
				for (int i=1;i<count; i++) {
					if (er.getCellData(this.DATA_SHEET, "registration", i) != "") {
						this.expectedResults.put(
								er.getCellData(this.DATA_SHEET, "registration", i), 
								new Car(
										er.getCellData(this.DATA_SHEET, "make", i),
										er.getCellData(this.DATA_SHEET, "colour", i),
										er.getCellData(this.DATA_SHEET, "registration", i)
										)
								);
					}
				}
				
			}
			else if (fileMap.get(fileName).getExtension().equals("csv")){
				LogHelper.debug("CSV Reader Not yet implemented");
			}
		}
    }	
	
	@After
    public void afterScenario(){
        this.driver.close();
    }
	
	
	@Given("^User is on Get Vehicle Info From \"([^\"]*)\"")
	public void user_is_on_Get_Vehicle_Info_From_on(String url) throws Throwable {
		LogHelper.debug("VehicleInfoTests::user_is_on_Get_Vehicle_Info_From_on: Navigating to "+ url);
		this.driver.navigate().to(url);
		LogHelper.debug("VehicleInfoTests::user_is_on_Get_Vehicle_Info_From_on: Maximize Window");
		this.driver.manage().window().maximize();
        lp = new LandingPage(this.driver);
	}

	@Given("^User clicks on Start Now$")
	public void user_clicks_on() throws Throwable {
		LogHelper.debug("VehicleInfoTests::user_clicks_on: Click on Start now");
		vp = lp.clickOnGetStarted();
	}
	

	@When("^User inserts\\s+([A-Z][A-Z]\\d\\d[A-Z][A-Z][A-Z])\\s+info into the search box$")
	public void user_inserts_info_into_the_search_box(String searchReg) throws Throwable {
		vp.fillSearchBox(searchReg);
		ip = vp.searchForResults();
	}

	@Then("^the correct vehicle information is displayed$")
	public void the_correct_vehicle_information_is_displayed() throws Throwable {
		LogHelper.debug("VehicleInfoTests::the_correct_vehicle_information_is_displayed: getting results");
		Car result = ip.getCarInfoFromResults();
		LogHelper.debug("VehicleInfoTests::the_correct_vehicle_information_is_displayed: Asserting that regnumber is present in test data");
		Assert.assertNotNull(this.expectedResults.get(result.getRegNumber()));
		LogHelper.debug("VehicleInfoTests::the_correct_vehicle_information_is_displayed: Asserting details for car are correct");
		Assert.assertEquals(this.expectedResults.get(result.getRegNumber()).getColour(), result.getColour());
		Assert.assertEquals(this.expectedResults.get(result.getRegNumber()).getMake(), result.getMake());
		Assert.assertEquals(this.expectedResults.get(result.getRegNumber()).getRegNumber(), result.getRegNumber());
		
	}
	
	


	@When("^User inserts an invalid registration plate info into the search box$")
	public void user_inserts_an_invalid_registration_plate_info_into_the_search_box() throws Throwable {
		vp.fillSearchBox("THIS IS AN INVALID LICENSE PLATE");
		ip = vp.searchForResults();
	}

	@Then("^the correct error message is displayed$")
	public void the_correct_error_message_is_displayed() throws Throwable {
		Assert.assertEquals("You must enter your registration number in a valid format", vp.getErrorFromSearch());
	}

}
