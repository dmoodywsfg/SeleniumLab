package seleniumlabs.waits;

import java.net.MalformedURLException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WaitTesting {
	static private String dir = "file:///C:/SeleniumLabs/Starters/";
	static private String htmlFile = dir + "WaitAlerts.html";

	WebDriver driver;

	@AfterTest 
	public void shutdown() {
		driver.quit();
		driver = null;
	}
	
	@BeforeTest
	public void startup() {
		driver = new ChromeDriver();
	}
	
	@Test
	public void handleAlerts() throws InterruptedException {
		
		// Explicit Wait: allows us to specify the Condition to Wait on
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		driver.get(htmlFile);
		System.out.println("Displaying page " + driver.getTitle());
		Thread.sleep(2000);

		driver.findElement(By.id("needWait")).click();			// Open Alert
									
		wait.until(ExpectedConditions.alertIsPresent());		// wait for Alert Popup
		
		Alert alert = driver.switchTo().alert();
		alert.accept();

		Thread.sleep(3000);
	}
}
