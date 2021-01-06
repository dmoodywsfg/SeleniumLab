package seleniumlabs.windows;


import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MultipleWindowTests {
	
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
	public void switchWindows() throws InterruptedException {

//		driver.get("http://the-internet.herokuapp.com/windows");
//		driver.get("https://demoqa.com/automation-practice-switch-windows-2/");
		driver.get("https://demoqa.com/browser-windows/");
		System.out.println("\nHandle Multiple Windows at: " + driver.getTitle());

		String parentWin = driver.getWindowHandle();			// save parent window id
		System.out.println("Parent Window ID: " + parentWin);

//		driver.findElement(By.linkText("Click Here")).click();  // Open new window
//		driver.findElement(By.id("button1")).click();			// Open new window
		driver.findElement(By.id("windowButton")).click();			// Open new window

		Set<String> winIds = driver.getWindowHandles();
		System.out.println("Window Handles After Click: ");
		for (String handle1 : winIds) {
			Thread.sleep(1000);
			driver.switchTo().window(handle1);
			System.out.println("\t" + handle1 + " - " + driver.getTitle());
		}		
		Thread.sleep(1000);
		String newWin = winIds.toArray(new String[0])[0];		// get idx 0, parent window is first, risky biz'ness... 
																// assuming parent is 0, but some browsers use alphabetical order
		System.out.println("\n winIds.toArray(new String[0])[0]: " + newWin);
		driver.switchTo().window(newWin);						// switch to new window
		System.out.println(" In New Window: " + driver.getWindowHandle() + " - " + driver.getTitle());
		Thread.sleep(2000);										// so you can see what happened
		
		driver.switchTo().window(parentWin);   					// switch back to parent using saved win handle
		System.out.println("\n Back in Parent Window: " + driver.getWindowHandle() + " - " + driver.getTitle() +"\n");
	}
}
