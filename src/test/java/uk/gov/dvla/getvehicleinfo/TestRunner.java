package uk.gov.dvla.getvehicleinfo;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import uk.gov.dvla.utils.FileDirParseService;
import uk.gov.dvla.utils.TestFileObject;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "Features" , 
		glue={"uk.gov.dvla.getvehicleinfo"},
		plugin = {"pretty", "html:target/cucumber-html-report"}
		)
public class TestRunner {

}
