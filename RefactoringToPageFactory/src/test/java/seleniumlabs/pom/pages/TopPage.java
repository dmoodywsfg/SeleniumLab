package seleniumlabs.pom.pages;

import org.openqa.selenium.WebDriver;

public abstract class TopPage {
	WebDriver driver;
	
	public TopPage(WebDriver webdriver)  {
		driver = webdriver;
	}
	
	public void quit() {
		driver.quit();
	}
	
	public abstract boolean onPage();
}
