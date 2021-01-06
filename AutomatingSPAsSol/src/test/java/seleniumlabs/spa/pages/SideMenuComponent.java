package seleniumlabs.spa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumlabs.spa.pages.TopPage.PageName;

/** 
 *  Represents the fixed side menu visible from all the pages.
 *  TopPage holds a static reference to SideMenuComponent for
 *  use by all other pages, so there is no duplication of the
 *  code needed to use the side menu. 
 * */
public class SideMenuComponent  {
	private WebDriver driver;
	private WebDriverWait wait;

	public SideMenuComponent(WebDriver webdriver, WebDriverWait wdWait) {
		driver = webdriver;
		wait = wdWait;
	}

	public AdminUsersPage selectAccountsAdmins() {
		
		WebElement accounts = driver.findElement(
				By.xpath("//*[@id='social-sidebar-menu']/li[5]/a"));
		accounts.click();		// activate pull down account menu
		WebElement admins = driver.findElement(
				By.xpath("//*[@id='ACCOUNTS']/li[1]/a"));
		
		admins.click();
		return (AdminUsersPage)TopPage.getPage(PageName.AdminUsersPage, driver);   	
	}
	
	public LoginPage logout() {
		WebElement logout = driver.findElement(By.xpath("//li[@id='logout']/a"));
		System.out.println("logout got logout: " + logout);
		try {
			WebElement closer = driver.findElement(By.cssSelector("span.glyphicon.glyphicon-remove"));
			closeInfoBox(closer);
			System.out.println("logout got logout: " + logout  + " Close closer: " + closer);
		} catch(NoSuchElementException ex)  {
			System.out.println("Locating and Closing Closer got exception " + ex);
		}

		System.out.println("logout wait for WebElement logout: to be clickable ");
		wait.until(ExpectedConditions.elementToBeClickable(logout));
		System.out.println("logout clicking button ");
		logout.click();

		System.out.println("returning LoginPage");

		return (LoginPage)TopPage.getPage(PageName.LoginPage, driver);   	
	}

	public boolean closeInfoBox(WebElement closer)  {
		boolean success = false;
		System.out.println("closeInfoBox got WebElement: " + closer + " at point: " + closer.getLocation());
		System.out.println("closeInfoBox move to WebElement ");
		Actions builder = new Actions(driver);
		System.out.println(closer.getText());

		builder.moveToElement(closer);
		builder.build().perform();
		System.out.println("closeInfoBox wait for WebElement: to be clickable ");

		wait.until(ExpectedConditions.elementToBeClickable(closer));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("closeInfoBox thread.sleep three error");
		}
		closer.click();
		wait.until(ExpectedConditions.stalenessOf(closer));
		System.out.println("closeInfoBox returning: " + success);

		return success;
	}

}
