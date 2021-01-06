package seleniumlab.testngparallel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParallelTesting {
	private WebDriver driver;

	
	@Test
	@Parameters("browser")			// parameter values in testng.xml
	public void parallelTesting(String browser) {
		if(browser == null)  {
			throw new IllegalArgumentException("Browser value is Null!");
		}
		switch(browser)  {
			case "chrome" : 
				driver = new ChromeDriver();
				break;
			case "firefox" : 
				driver = new FirefoxDriver();
				break;
		    default :
				throw new IllegalArgumentException("Unknown Brwoser value " + browser);
		}
		driver.get("http://www.google.com"); 
		WebElement input = driver.findElement(By.name("q"));
		input.sendKeys("selenium");

		if (driver != null) {
			driver.quit();
		}
	}

}
