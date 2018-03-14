package uk.gov.dvla.businessSpecificClasses;

public class Car {
	private String make;
	private String colour;
	private String regNumber;

	public Car(String make, String colour, String regNumber) {
		this.make = make;
		this.colour = colour;
		this.regNumber = regNumber;
	}
	
	@Override
	public String toString() {
		return "Car [make=" + make + ", colour=" + colour + ", regNumber=" + regNumber + "]";
	}

	public String getMake() {
		return make;
	}

	public String getColour() {
		return colour;
	}

	public String getRegNumber() {
		return regNumber;
	}
	
}
