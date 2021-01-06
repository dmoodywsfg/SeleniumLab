package seleniumlabs.webelements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdventureTours {								// page URLs in static consts at top of class
	public static final String appURL = "http://localhost:8080/mtours/";
	public static final String flightFindURL = "http://localhost:8080/mtours/reservation.html";

	@Test
	public void bookFlightHard() throws InterruptedException {
		System.out.println("Running Test bookFlight!");
		
		WebDriver driver = new ChromeDriver();
		driver.get(appURL);              
														// assert we are in the right place
		Assert.assertEquals("Welcome: Adventure Tours", driver.getTitle());
		Assert.assertEquals(appURL, driver.getCurrentUrl());
		
		// One Adventure Tours Home Page
		//===============================
		WebElement name = driver.findElement(By.name("userName"));
		WebElement passwrd = driver.findElement(By.name("password"));
		WebElement signIn = driver.findElement(By.name("submit"));

		name.sendKeys("guest");
		passwrd.sendKeys("adventure");
		signIn.click();               // use a button... the hard way  :~(

		Assert.assertTrue(driver.getCurrentUrl().startsWith(flightFindURL));
		Assert.assertEquals(driver.getTitle(), "Find a Flight: Adventure Tours:");

		// On Flight Finder Page
		//===============================
		Thread.sleep(3000);
		driver.quit();
	}
	
	@Test
	public void bookFlightEasy() throws InterruptedException {
		System.out.println("Running Test bookFlightEasy!");
		
		WebDriver driver = new ChromeDriver();
		driver.get(appURL);              
														// assert we are in the right place
		Assert.assertEquals("Welcome: Adventure Tours", driver.getTitle());
		Assert.assertEquals(appURL, driver.getCurrentUrl());
		
		// One Adventure Tours Home Page
		//===============================
		WebElement name = driver.findElement(By.name("userName"));
		WebElement passwrd = driver.findElement(By.name("password"));

		name.sendKeys("guest");
		passwrd.sendKeys("adventure");
		name.submit();									// submit on already found element.... easy!

		Assert.assertTrue(driver.getCurrentUrl().startsWith(flightFindURL));
		Assert.assertEquals(driver.getTitle(), "Find a Flight: Adventure Tours:");

		// On Flight Finder Page
		//===============================
		Thread.sleep(3000);
		driver.quit();
	}

	@Test
	public void test3()   {
		System.out.println("Running Test 3!");
	}

	@Test
	public void test4()   {
		System.out.println("Running Test 4!");
	}
	
	@Test
	public void test5()   {
		System.out.println("Running Test 5!");
	}


}
