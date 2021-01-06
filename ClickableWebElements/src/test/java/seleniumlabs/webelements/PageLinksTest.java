package seleniumlabs.webelements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PageLinksTest {
	public static final String appURL = "http://localhost:8080/mtours/";
	public static final String underConstruction= "Under Construction: Adventure Tours" ;

	@Test
	public void checkLinks() throws InterruptedException {
		WebDriver driver = new ChromeDriver();

		driver.get(appURL);
		Assert.assertEquals(driver.getTitle(), "Welcome: Adventure Tours");

		// One Adventure Tours Home Page
		// ===============================
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Links: " + links);
		
//		checkLinksBadly(driver, links);
		checkLinksCorrectly(driver, links);		

		Thread.sleep(3000);
		driver.quit();
	}

	private void checkLinksBadly(WebDriver driver, List<WebElement> links) {
		System.out.println("\n In checkLinksBadly()");		

		for (WebElement welm :links)  {      
			System.out.print("Testing Link \"" + welm.getText() + "\"");
			welm.click();
			System.out.println("Directs to : " + driver.getTitle());
			
			if (driver.getTitle().equals(underConstruction))  {
				System.out.println("\t Directs to Under Construction.");
			}
			else if (driver.getTitle().equals("404 Not Found"))  {
				System.out.println("\t Link Is Broken");		
			}
			else  {
				System.out.println("\t Link Is Working");
			}
			driver.navigate().back();
		}
	}

	private void checkLinksCorrectly(WebDriver driver, List<WebElement> links) {
		System.out.println("\n In checkLinksCorrectly()");
		
		String[] linkTexts = new String[links.size()];
		int i=0;
		
		for (WebElement welm :links)  {          // avoid StaleElementReferenceException
			linkTexts[i++] =  welm.getText();	 // Doesn't work if using image instead of text!
		}										 // if have influence, put an attribute on all links... 
		for (String linkText : linkTexts)  {
			driver.findElement(By.linkText(linkText)).click();   // get fresh reference to WebElement
		  													// follow the link
			System.out.println("Testing Link \"" + linkText + "\"" + " Directs to : " + driver.getTitle());
			
			if (driver.getTitle().equals(underConstruction))  {
				System.out.println("\t Directs to Under Construction.");
			}
			else if (driver.getTitle().equals("404 Not Found"))  {
				System.out.println("\t Link Is Broken");		
			}
			else  {
				System.out.println("\t Link Is Working");
			}
			driver.navigate().back();
		}
	}

}
