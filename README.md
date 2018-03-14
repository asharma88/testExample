# Example Test Framework using Java, Cucumber & Log4j

- Uses POM - /src/main/java/uk/gov/dvla/pageObjects/
- Uses Cucumber JVM - src/test/java/uk/gov/dvla/getvehicleinfo
- Feature files - /Features/
- Recursive File/Directory Parser (Note, do not point this as C:\ - StackOverflow) - /src/test/java/uk/gov/dvla/utils/FileDirParseService.java	

Ps. I've never used Cucumber JVM, always used Behave in Python (similar BDD framework).

Questions/Statements:

- I didn't get around to impelmented CSV reading.
- Reading the xls in the Before Scenario section seems a bit dirty, probably worth looking at a more efficient way of doing this. I tried to use BeforeClass and found that is not supported in Cuccumber JVM.
- It would be interesting to find out why you'd want to use XLS/CSV files when using Cucumber files. I mean you can keep all the test inputs and the results all in one place under examples.
- I was able to send a POST request to http://vehicleenquiry.service.gov.uk/ConfirmVehicle?Vrm=af51klk&Continue= which returns the page, could be possible to read the page into a HTML DOM parser and Assert the fields (depending on whether you're trying to verify the data or the platform).
