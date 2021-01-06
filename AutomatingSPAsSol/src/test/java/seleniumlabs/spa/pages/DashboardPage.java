package seleniumlabs.spa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends TopPage {
	static final String PageTitle = "Dashboard";

	public DashboardPage(WebDriver driver) {
		super(driver);
		System.out.println("new DashboardPage");
	}

	public boolean onPage()  {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ACCOUNTS")));
		return driver.getTitle().equals(PageTitle);
	}

	public boolean userLogedIn(String userName) {
		WebElement displayName = getUserName();
		System.out.println("userLogedIn got displayName: " + displayName);
		System.out.println("userLogedIn given name : [" + userName + "] displayed Name: [" + displayName.getText() + "]");
		if (userName.equalsIgnoreCase(displayName.getText())) {
			return true;
		}
		return false;
	}	
	
	// Helper Methods: Locate different WebElements on page
	// =========================================================
	
	private WebElement getUserName() {
		return driver.findElement(By.xpath("//*[@id='sidebar']/div[2]/a/p/strong"));
	}
	
}
