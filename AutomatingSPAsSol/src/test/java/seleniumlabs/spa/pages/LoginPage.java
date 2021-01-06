package seleniumlabs.spa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends TopPage {
	static final String Url = "https://www.phptravels.net/admin";
	static final String HomePageTitle = "Administator Login";
	
	public LoginPage() {
		super();
		setHomePage(this);		// tell TopPage about us
		System.out.println("new LoginPage");
	}

	public boolean automationOK() {
		return driver!=null;
	}

	public boolean loadPage() {
		driver.manage().window().maximize();
		driver.get(Url);
		return onPage();
	}

	@Override
	public boolean onPage()  {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));
		return driver.getTitle().equals(HomePageTitle);
	}
	
	public DashboardPage login(String userID, String pwd) {
		WebElement userTextInput = driver.findElement(By.name("email"));
		WebElement passwdTextInput = driver.findElement(By.name("password"));
		userTextInput.sendKeys(userID);
		passwdTextInput.sendKeys(pwd);	
		
		userTextInput.submit();
		return (DashboardPage)getPage(PageName.DashboardPage, driver);   	
	}
}
