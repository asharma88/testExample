# Test Framework using Java, Cucumber, Selenium & Log4j

This is a  test project which utilises  Cucumber, Java, Selenium and Log4j for testing `http://vehicleenquiry.service.gov.uk/`


# Run Instructions
- Checkout repository
- Ensure you have Geckodriver on your system path.
- Run `mvn clean;mvn test`

# Structure
- Logs - /logs/test_run.log (left in repo on purpose to show log examples)
- POM - /src/main/java/uk/gov/dvla/pageObjects/*
- Cucumber - src/test/java/uk/gov/dvla/getvehicleinfo/*
- Feature - /Features/VehicleInfo.feature
- Recursive File/Directory Parser - /src/test/java/uk/gov/dvla/utils/FileDirParseService.java
- Logging - /src/test/java/log4j.properties
- ExcelUtils - /src/test/java/uk/gov/dvla/utils/ExcelReader.java

# TODO/Notes
- TODO : Add factory function to return WebDriver depening on arguments passed (for example, Chrome or Mozilla, etc.)
- TODO : Code comments
- TODO : Implement CSV reading.
- TODO : Move reading testdata from BeforeScenario to a static function which is called in the beforeScenario, however it runs once if the testdata HashMap is empty.
- Sending a POST request `http://vehicleenquiry.service.gov.uk/ConfirmVehicle?Vrm=af51klk&Continue=` returns the result page, might be more efficient to use a html DOM parser and extract the data and run asserts. (This really depends on whether we're testing the system or we're testing the data.)
