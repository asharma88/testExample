# Example Test Framework using Java, Cucumber, Selenium & Log4j

This is a  test project which utilises  Cucumber, Java, Selenium and Log4j for testing `http://vehicleenquiry.service.gov.uk/`


# Run Instructions
- Checkout repository
- Ensure you have Geckodriver on your system path.
- Run `mvn clean;mvn test`

# TODO/Notes
- TODO : Code comments
- TODO : Implement CSV reading.
- TODO : Move reading testdata from BeforeScenario to a static function which is called in the beforeScenario, however it runs once if the testdata HashMap is empty.
- Sending a POST request to `http://vehicleenquiry.service.gov.uk/ConfirmVehicle?Vrm=af51klk&Continue=` which returns the page, might be more efficient to use a html DOM parser and extracting the data and asserting. (This really depends on whether we're testing the system or we're testing the data.)
