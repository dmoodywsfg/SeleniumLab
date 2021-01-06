package seleniumlabs.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FlightSelectorPage extends TopPage {
	static String PageTitle = "Select a Flight: Adventure Tours";

	public FlightSelectorPage(WebDriver driver) {
		super(driver);
	}

	public boolean onPage()  {
		return driver.getTitle().equals(PageTitle);
	}

	public static String UnifiedAirlines = "Unified Airlines";
	public static String PangnaAirlines = "PangnaAirlines";
	public static String BlueSkiesAirlines = "BlueSkiesAirlines";
	
	public void selectDepartureAirline(String airline) {
		String xpathStart = "//input[@name='outFlight'][contains(@value, '";
		String xpathEnd = "')]";
		String xpathOutFlight = xpathStart + airline + xpathEnd;
		WebElement flight = driver.findElement(By.xpath(xpathOutFlight));
		flight.click();
	}

	public void selectReturnAirline(String airline) {
		String xpathStart = "//input[@name='inFlight'][contains(@value, '";
		String xpathEnd = "')]";
		String xpathInFlight = xpathStart + airline + xpathEnd;
		WebElement flight = driver.findElement(By.xpath(xpathInFlight));
		flight.click();
	}

	public PassengerInformationPage sumbitFlightSelections() {
		WebElement form = driver.findElement(By.name("results"));
		form.submit();
		return new PassengerInformationPage(driver);			// return instance of PassengerInformationPage
	}

}
