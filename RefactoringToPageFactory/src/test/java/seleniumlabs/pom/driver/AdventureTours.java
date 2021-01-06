package seleniumlabs.pom.driver;

import org.testng.Assert;
import org.testng.annotations.Test;

import seleniumlabs.pom.pages.FlightFinderPage;
import seleniumlabs.pom.pages.FlightSelectorPage;
import seleniumlabs.pom.pages.HomePage;
import seleniumlabs.pom.pages.PassengerInformationPage;

public class AdventureTours {
	private HomePage homePage = null;
	private FlightFinderPage flghtFinderPg = null;
	private FlightSelectorPage flghtSelectPg = null;
	private PassengerInformationPage passInfoPg = null;

	@Test
	public void bookFlight() throws InterruptedException {
		homePage = HomePage.openHomePage();
		Assert.assertTrue(homePage.loadPage(), "Could not load home page");

		// One Adventure Tours Home Page
		// ===============================
		flghtFinderPg = homePage.login("guest", "adventure");
		Assert.assertTrue(flghtFinderPg.onPage(), "Could not Login to Flight Finder page");

		// One Flight Finder Page
		// ===============================
		flghtFinderPg.setTripType(FlightFinderPage.RoundTrip);
		flghtFinderPg.setFlight(2, "Frankfurt", "Paris", "11", "20", "12", "1");
		flghtFinderPg.setClass(FlightFinderPage.FirstClass);
		flghtFinderPg.setAirline(FlightFinderPage.UnifiedAirlines);

		flghtSelectPg = flghtFinderPg.sumbitFlightCriteria();
		Assert.assertTrue(flghtSelectPg.onPage(), "Could not Login to Flight Selector page");
		Thread.sleep(2000);     // wait so you can see what happened

		// One Adventure Tours Flight Selector
		// ==================================
		flghtSelectPg.selectDepartureAirline(FlightSelectorPage.UnifiedAirlines);
		flghtSelectPg.selectReturnAirline(FlightSelectorPage.UnifiedAirlines);
		
		passInfoPg = flghtSelectPg.sumbitFlightSelections();
		Assert.assertTrue(passInfoPg.onPage(), "Could not access Passenger Info page");
		Thread.sleep(2000);     // wait so you can see what happened

		flghtSelectPg.quit();   // use any page call TopPage.quit()
	}

}
