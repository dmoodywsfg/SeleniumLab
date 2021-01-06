package seleniumlabs.spa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminUsersPage extends TopPage {
	static String PageTitle = "Admins Management";

	public AdminUsersPage(WebDriver driver) {
		super(driver);
		System.out.println("new AdminUsersPage");
	}

	public boolean onPage() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("add_button")));
		return driver.getTitle().equals(PageTitle);
	}

	public AdminAddUserPage addAdmin() {
		WebElement addbutton = driver.findElement(By.className("add_button"));

		addbutton.click();		// go to AddUser page
		return (AdminAddUserPage)getPage(PageName.AdminAddUserPage, driver);   	
	}
	
	public boolean userExists(String firstName, String lastName, String email) {
		boolean exists = false;
		WebElement row = driver.findElement(By.xpath("//tbody/tr[1]"));
		if (matchFrstName(firstName, row) && matchLastName(lastName, row) && matchEmail(email, row))  {
			exists = true;
		}
		return exists;
	}

	// Helper Methods:
	// =========================================================
	
	private boolean matchFrstName(String fname, WebElement row) {
		boolean match = false;
		WebElement val = row.findElement(By.xpath("td[3]"));
		System.out.println("matchFrstName: Given val: " + fname +  "  webElement val: " + val.getText());
		if (fname.equals(val.getText()))
			match = true;
		System.out.println("matchFrstName returning" + match);
		return match;
	}
	private boolean matchLastName(String lname, WebElement row) {
		boolean match = false;
		WebElement val = row.findElement(By.xpath("td[4]"));
		System.out.println("matchLastName: Given val: " + lname +  "  webElement val: " + val.getText());
		if (lname.equals(val.getText()))
			match = true;
		System.out.println("matchLastName returning" + match);
		return match;
	}

	private boolean matchEmail(String email, WebElement row) {
		boolean match = false;
		WebElement val = row.findElement(By.xpath("td[5]"));
		System.out.println("matchEmail: Given val: " + email +  "  webElement val: " + val.getText());
		if (email.equals(val.getText()))
			System.out.println("matchEmail returning" + match);
			match = true;
		return match;
	}

}
