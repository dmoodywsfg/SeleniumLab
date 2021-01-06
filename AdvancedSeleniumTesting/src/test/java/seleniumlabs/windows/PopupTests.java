package seleniumlabs.windows;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PopupTests {
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
		driver.get("http://the-internet.herokuapp.com/javascript_alerts");
		System.out.println("Handle Alerts at: " + driver.getTitle());

		List<WebElement> webels = driver.findElements(By.tagName("button"));
		Alert alert = null;
		
		System.out.println("Alert Buttons: ");
		for (WebElement el : webels)  {
			String alertType = el.getAttribute("onClick");
			System.out.println("\t" + alertType);
			el.click();
			alert = driver.switchTo().alert();
			Thread.sleep(1000);

			switch(alertType)  {
			case "jsAlert()":						// handle simple alert
				alert.accept();						// click alert OK 
				System.out.println("\t\t Results: " + driver.findElement(By.id("result")).getText());
				break;
			case "jsConfirm()":						// handle confirmation alert
				alert.dismiss();					// click on cancel, accept() hits ok
				System.out.println("\t\t Results: " + driver.findElement(By.id("result")).getText());
				break;
			case "jsPrompt()":						// handle prompt alert
				alert.sendKeys("Filling In Text Box!");
				alert.accept();
				System.out.println("\t\t Results: " + driver.findElement(By.id("result")).getText());
				break;
			default:
				System.err.println("\t No Case for button element: " + el.getAttribute("onClick"));
			}
		}
	}

}
