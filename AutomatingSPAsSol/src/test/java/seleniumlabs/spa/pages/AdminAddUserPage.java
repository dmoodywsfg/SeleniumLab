package seleniumlabs.spa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class AdminAddUserPage extends TopPage {
	static String PageTitle = "Add Admin";

	public AdminAddUserPage(WebDriver driver) {
		super(driver);
		System.out.println("new AdminAddUserPage");
	}

	public boolean onPage() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("fname")));
		return driver.getTitle().equals(PageTitle);
	}

	public void enterUserCreds(String fName, String lName, String email, String pass) {
		getFirstName().sendKeys(fName);
		getLastName().sendKeys(lName);
		getEmail().sendKeys(email);
		getPass().sendKeys(pass);
	}

	public void enterUserLocation(String mobile, String country, String addStreet, String addState) {
		getMobileNum().sendKeys(mobile);
		Select cntry = getCountry();
		cntry.selectByVisibleText(country);
		getAddr1().sendKeys(addStreet);
		getAddr2().sendKeys(addState);
	}

	public AdminUsersPage addNewAdmin(boolean active, boolean subscribe ) {
		Select enable = getEnabled();
		if (active)  {
			enable.selectByValue("yes");
		} else {
			enable.selectByValue("no");
		}
		if (subscribe)  {
			doSubscribe();
		} else {
			doNotSubscribe();
		}
		getSubmitBut().click();
				
		return (AdminUsersPage)getPage(PageName.AdminUsersPage, driver);   	
	} 

	// Helper Methods: Locate different WebElements on page
	// =========================================================

	private WebElement getFirstName() {
		return driver.findElement(By.name("fname"));
	}
	private WebElement getLastName() {
		return driver.findElement(By.name("lname"));
	}
	private WebElement getEmail() {
		return driver.findElement(By.name("email"));
	}
	private WebElement getPass() {
		return driver.findElement(By.name("password"));
	}
	private WebElement getMobileNum() {
		return driver.findElement(By.name("mobile"));
	}
	private WebElement getAddr1() {
		return driver.findElement(By.name("address1"));
	}
	private WebElement getAddr2() {
		return driver.findElement(By.name("address2"));
	}
	private Select getCountry() {
		return new Select(driver.findElement(By.name("country")));
	}
	private Select getEnabled() {
		return new Select(driver.findElement(By.name("status")));
	}
	private void doSubscribe() {
		WebElement ss = driver.findElement(By.name("newssub"));
		if (!ss.isSelected()) ss.click();
	}
	private void doNotSubscribe() {
		WebElement ss = driver.findElement(By.name("newssub"));
		if (ss.isSelected()) ss.click();
	}
	private WebElement getSubmitBut() {
		return driver.findElement(By.className("btn-primary"));
	}

}
