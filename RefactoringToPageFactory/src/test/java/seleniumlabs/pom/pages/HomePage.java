package seleniumlabs.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends TopPage {
	static String Url = "http://localhost:8080/mtours/";
	static String HomePageTitle = "Welcome: Adventure Tours";
	
	@FindBy(name = "userName")
	private WebElement userTextInput;
	
	@FindBy(name = "password")
	private WebElement passwdTextInput;
	
	public static HomePage openHomePage() {
		WebDriver driver = new ChromeDriver();
		return new HomePage(driver);
	}

	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean loadPage() {
		driver.get(Url);
		return onPage();
	}

	public boolean onPage()  {
		return driver.getTitle().equals(HomePageTitle);
	}
	
	public FlightFinderPage login(String userID, String pwd) {
		userTextInput.sendKeys(userID);
		passwdTextInput.sendKeys(pwd);	
		
		userTextInput.submit();
		return new FlightFinderPage(driver);
	}

}
