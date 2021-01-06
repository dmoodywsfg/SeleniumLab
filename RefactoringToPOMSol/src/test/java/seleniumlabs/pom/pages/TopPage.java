package seleniumlabs.pom.pages;

import org.openqa.selenium.WebDriver;

public abstract class TopPage {
	WebDriver driver;
	
	public TopPage(WebDriver webdriver)  {
		driver = webdriver;
	}
	
	// every pageObject will be able to expose a quit() method to the TestDriver
	// never know when TestDriver will want to end the test
	public void quit() {
		driver.quit();
	}
	
	public abstract boolean onPage();
	
	// want method to keep track of page instances
	// don't want to create a new instance everytime we revisit a page

}
