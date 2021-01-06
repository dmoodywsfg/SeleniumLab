package seleniumlab.basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class FirstScript {
	
	private static void clickTabLink(WebDriver driver, String hrefLink) throws InterruptedException {
		WebElement href = driver.findElement(By.linkText(hrefLink));
		href.click();													// click on link
		Thread.sleep(1000);                                 // wait so you can see what happened
		driver.navigate().back();							// go bak to main page
	}

	public static void main(String[] args) throws InterruptedException {
//		WebDriver driver = new FirefoxDriver();
		WebDriver driver = new ChromeDriver();
//		WebDriver driver = new EdgeDriver();
//		WebDriver driver = new InternetExplorerDriver();
//		WebDriver driver = getDriver(); 
		
		driver.get("https://www.selenium.dev/");            // open browser and navigate
		System.out.println("Displaying page " + driver.getTitle() + driver.getCurrentUrl());
		Thread.sleep(1000);                                 // wait so you can see what happened
		
		clickTabLink(driver, "Downloads");
		clickTabLink(driver, "Projects");
		clickTabLink(driver, "Documentation");
		clickTabLink(driver, "Support");
		clickTabLink(driver, "Blog");
		System.out.println("Now Displaying page " + driver.getTitle() + driver.getCurrentUrl());

		driver.close();
	}

	
	// alternative way to set location of a WebDriver
	//
	static public WebDriver getDriver() {
		System.setProperty("webdriver.edge.driver","C:\\SeleniumLabs\\SeleniumServers\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		
		System.setProperty("webdriver.gecko.driver", "C:\\SeleniumLabs\\SeleniumServers\\geckodriver.exe");
//		WebDriver driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumLabs\\SeleniumServers\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		return driver;
	}
}


