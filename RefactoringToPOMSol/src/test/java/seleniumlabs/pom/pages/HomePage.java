package seleniumlabs.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class HomePage extends TopPage {
	static String Url = "http://localhost:8080/mtours/";
	static String HomePageTitle = "Welcome: Adventure Tours";

	public static HomePage openHomePage() {
		WebDriver driver = new ChromeDriver();
		return new HomePage(driver);
	}

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public boolean loadPage() {
		driver.manage().window().maximize();
		driver.get(Url);
		return onPage();
	}

	@Override
	public boolean onPage()  {
		return driver.getTitle().equals(HomePageTitle);
	}
	
	public FlightFinderPage login(String userID, String pwd) {
		WebElement userTextInput = driver.findElement(By.name("userName"));
		WebElement passwdTextInput = driver.findElement(By.name("password"));
		userTextInput.sendKeys(userID);
		passwdTextInput.sendKeys(pwd);	
		
		userTextInput.submit();
		return new FlightFinderPage(driver);
	}
}
