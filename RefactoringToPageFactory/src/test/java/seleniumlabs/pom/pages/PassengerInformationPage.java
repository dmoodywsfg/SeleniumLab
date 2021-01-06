package seleniumlabs.pom.pages;

import org.openqa.selenium.WebDriver;

public class PassengerInformationPage extends TopPage {
	static String PageTitle = "Passenger Info: Adventure Tours";

	public PassengerInformationPage(WebDriver driver) {
		super(driver);
	}

	public boolean onPage()  {
		return driver.getTitle().equals(PageTitle);
	}

}
