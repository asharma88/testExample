Feature: Get Vehicle Information

Scenario Outline: Successful vehicle info retrieval with valid registration number
	Given User is on Get Vehicle Info From "https://www.gov.uk/get-vehicle-information-from-dvla"
	And User clicks on Start Now
	When User inserts <registration> info into the search box
	Then the correct vehicle information is displayed
Examples:
		| registration |
		| AF51KLK      |
	
Scenario: Unsuccessful vehicle info retrieval with invalid registration number
	Given User is on Get Vehicle Info From "https://www.gov.uk/get-vehicle-information-from-dvla"
	And User clicks on Start Now
	When User inserts an invalid registration plate info into the search box
	Then the correct error message is displayed