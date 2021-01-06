package seleniumlabs.webelements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdventureTours {
	public static final String appURL = "localhost:8080/mtours/";
	public static final String homePageTitle = "Welcome: Adventure Tours";
	public static final String flightFindURL = "http://localhost:8080/mtours/reservation.html";
	public static final String flightFindPageTitle   = "Find a Flight: Adventure Tours:";
	public static final String flightSelectPageTitle = "Select a Flight: Adventure Tours";
	
	static int roundTrip = 0; 				// "code should read like well written prose" - Robert Martin
	static int oneWay = 1;

	@Test
	public void bookFlight() throws InterruptedException {
		WebDriver driver = new ChromeDriver();

		driver.get(appURL); 
		Assert.assertEquals(driver.getTitle(), homePageTitle);

		// One Adventure Tours Home Page
		//===============================
		WebElement name = driver.findElement(By.name("userName"));
		WebElement passwrd = driver.findElement(By.name("password"));
		// WebElement signIn = driver.findElement(By.name("login"));

		name.sendKeys("guest");
		passwrd.sendKeys("adventure");
		name.submit();
		// signIn.click();

		Assert.assertTrue(driver.getCurrentUrl().startsWith(flightFindURL));	
		Assert.assertEquals(driver.getTitle(), flightFindPageTitle);
		
		// On Flight Finder Page
		//===============================
													// locate all our WebElements first
													// our Radio Buttons
		List<WebElement> radiotripType = driver.findElements(By.name("tripType"));
		List<WebElement> radioCategory = driver.findElements(By.name("servClass"));
		
													// our Select controls
		Select numPassengers = new Select(driver.findElement(By.name("passCount")));
		Select fromPort  = new Select(driver.findElement(By.name("fromPort")));
		Select fromMonth = new Select(driver.findElement(By.name("fromMonth")));
		Select fromDate  = new Select(driver.findElement(By.name("fromDay")));
		Select toPort    = new Select(driver.findElement(By.name("toPort")));
		Select toMonth   = new Select(driver.findElement(By.name("toMonth")));
		Select toDate    = new Select(driver.findElement(By.name("toDay")));
		Select airline   = new Select(driver.findElement(By.name("airline")));
   
		// use WebElements to manipulate the page 
		
		// notice how using constants for radio button index makes it easy to read the
		//    code.  Compare this with the radioCategory below, where we use the index
		if (radiotripType.get(oneWay).isSelected()) {     // if 1-way trip selected
			radiotripType.get(roundTrip).click();         // want round trip
		} else {                                          // static int roundTrip = 0; 
			radiotripType.get(roundTrip).click();         // static int oneWay = 1;
		}                      
		String Frankfurt = "Frankfurt"; 	
		numPassengers.selectByValue("2");        		  // 2 passengers
		fromPort.selectByValue(Frankfurt);      		  // leaving from Frankfurt on Nov 20th
		fromMonth.selectByValue("11");             
		fromDate.selectByValue("20");
		toPort.selectByValue("Paris");            		  // arriving in Paris Dec 1st
		toMonth.selectByValue("12");
		toDate.selectByValue("1");
												  		  // Compare below with rountTripType above
		if (radioCategory.get(0).isSelected() || radioCategory.get(1).isSelected()) {
			radioCategory.get(2).click();         		  // I want to fly first class
		} else {                                  		  // should use constants for these too
			radioCategory.get(2).click();         		  // Do Not use Magic Numbers!
		}
		Assert.assertTrue(radioCategory.get(2).isSelected());

		airline.selectByIndex(2);

		Thread.sleep(2000);                       // wait so you can see what happened
		radioCategory.get(0).submit();            // on to flight finder page

		Assert.assertEquals(driver.getTitle(), flightSelectPageTitle);

		// One Adventure Tours Flight Selector
		//==================================

		Thread.sleep(3000);
		driver.quit();
	}
}
