package seleniumlabs.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class FlightSelectorPage extends TopPage {
	static String PageTitle = "Select a Flight: Adventure Tours";
	
	@FindBy(name = "results")
	private WebElement form;

	public FlightSelectorPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
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
		WebElement flight = form.findElement(By.xpath(xpathOutFlight));
		flight.click();
	}

	public void selectReturnAirline(String airline) {
		String xpathStart = "//input[@name='inFlight'][contains(@value, '";
		String xpathEnd = "')]";
		String xpathInFlight = xpathStart + airline + xpathEnd;
		WebElement flight = form.findElement(By.xpath(xpathInFlight));
		flight.click();
	}

	public PassengerInformationPage sumbitFlightSelections() {
		WebElement form = driver.findElement(By.name("results"));
		form.submit();
		return new PassengerInformationPage(driver);
	}

}
