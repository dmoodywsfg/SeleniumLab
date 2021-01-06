package seleniumlabs.advwebaccess;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class AdvanceWebAccess {
	static private String testWebsite = "http://www.jqueryscript.net/demo/Feature-rich-Custom-jQuery-Context-Menu-Plugin-contextMenu/";
	WebDriver driver;
	
	@BeforeTest
	public void startup() {
		driver = new ChromeDriver();
	}
	@AfterTest 
	public void shutdown() {
		driver.quit();
		driver = null;
	}

	//  This method is a Good Candidate for your reusable test components!
	//
	private void takeScreenShot(WebDriver driver, String fName) throws IOException {

		File scrshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrshotFile, new File(fName));
	}

	@Test
	public void contextMenu() throws InterruptedException, IOException, AWTException {

		driver.get(testWebsite);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		System.out.println("Displaying page " + driver.getTitle() + driver.getCurrentUrl());	
		
		WebElement hotRClickArea = driver.findElement(By.className("context-menu-one"));
		wait.until(ExpectedConditions.elementToBeClickable(hotRClickArea));
		
		takeScreenShot(driver, "BeforeContext.png");
		
		Action rCtxtMenu = new Actions(driver)
				.contextClick(hotRClickArea)
				.build();
		rCtxtMenu.perform();

		takeScreenShot(driver, "DuringContext.png");

		rCtxtMenu = new Actions(driver)
				.sendKeys(Keys.ARROW_DOWN)
				.sendKeys(Keys.ARROW_DOWN)
				.sendKeys(Keys.ARROW_DOWN)
				.sendKeys(Keys.ARROW_DOWN)
				.sendKeys(Keys.ARROW_DOWN)
				.build();
		rCtxtMenu.perform();
		
		takeScreenShot(driver, "SelectionSet.png");
		Thread.sleep(1000); // wait so can see what happened

		rCtxtMenu = new Actions(driver)
				.sendKeys(Keys.ENTER)
				.build();
		rCtxtMenu.perform();
		
		Thread.sleep(1000); // wait so can see what happened
		
		// take a screenshot with an active alert
		//======================================================
		// Use Selenium TakeScreenshot, uses the DOM content. 
		// 		Alerts belongs to a separate window and won't be included 
		//takeScreenShot(driver, "AlertActive.png");		// Will throw an exception
		
		// Use  Robot Class if want to capture an active alert popup
		Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage image = new Robot().createScreenCapture(screen);
		ImageIO.write(image, "png", new File("AlertActive.png"));
				
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert Says: " + alert.getText());
		alert.accept();
		takeScreenShot(driver, "AfterContext.png");
	}
}
