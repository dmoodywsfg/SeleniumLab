package seleniumlab.grid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class FirstGridScript {
	// Desired Capabilities doc at: https://github.com/SeleniumHQ/selenium/wiki/DesiredCapabilities 

	private static void clickTabLink(WebDriver driver, String tabId) throws InterruptedException {
		WebElement tab = driver.findElement(By.id(tabId));
		WebElement href = tab.findElement(By.tagName("a"));
		href.click(); // click on link
		Thread.sleep(1000); // wait so you can see what happened
	}
	
	private static void testTemplate(DesiredCapabilities capability) throws InterruptedException, MalformedURLException  {
		URL myHub = new URL("http://localhost:4444/wd/hub");
		WebDriver driver = new RemoteWebDriver(myHub, capability);

		driver.get("https://www.seleniumhq.org/");	
		System.out.println("Displaying page " + driver.getTitle() + driver.getCurrentUrl());
		Thread.sleep(1000); 				

		clickTabLink(driver, "menu_download");
		clickTabLink(driver, "menu_documentation");
		clickTabLink(driver, "menu_support");

		driver.close();	
	}

	@Test()
	public static void SelenumGridFirefox() throws InterruptedException, MalformedURLException {
		
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		capability.setPlatform(Platform.WINDOWS);
		
		testTemplate(capability);
	}
	
	@Test()
	public static void SelenumGridCrome() throws InterruptedException, MalformedURLException {
		
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setBrowserName("chrome");
		capability.setPlatform(Platform.WINDOWS);
		
		testTemplate(capability);
	}
}
