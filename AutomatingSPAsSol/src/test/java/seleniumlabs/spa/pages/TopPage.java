package seleniumlabs.spa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class TopPage {
	static public enum PageName { LoginPage, AdminUsersPage, DashboardPage, AdminAddUserPage };
	static private LoginPage        loginPage = null;
	static private AdminUsersPage   adminUsersPg = null;
	static private DashboardPage    dashboardPg = null;
	static private AdminAddUserPage newAdminInfoPg = null;
	
	static private SideMenuComponent sideMenu;
	
	static protected WebDriverWait wait;
	protected WebDriver driver;
	
	protected TopPage(WebDriver webdriver)  {
		if (webdriver == null)  {
			throw new IllegalArgumentException("Given null WebDriver reference");
		}
		if (driver == null)  {				// should Never happen
			driver = webdriver;
		}
	}
	
	protected TopPage() {
		// driver = new FirefoxDriver();
		// driver = new InternetExplorerDriver();
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
		sideMenu = new SideMenuComponent(driver, wait); 
	}
	
	protected void setHomePage(LoginPage lpage) {
		loginPage = lpage;
	}

	public abstract boolean onPage();
	
	public AdminUsersPage selectAccountsAdmins() {
		return sideMenu.selectAccountsAdmins();
	}

	public LoginPage logout() {
		return sideMenu.logout();
	}

	public void quit() {
		driver.quit();
		driver = null;		
	}

	public static TopPage getPage(PageName page, WebDriver driver) {
		
		switch(page)  {
		case LoginPage : 
			if (loginPage == null)  {
				loginPage = new LoginPage();
			}
			System.out.println("going to LoginPage");
			return loginPage;
		case DashboardPage : 
			if (dashboardPg == null)  {
				dashboardPg = new DashboardPage(driver);
			}
			return dashboardPg;
		case AdminUsersPage : 
			if (adminUsersPg == null)  {
				adminUsersPg = new AdminUsersPage(driver);
			}
			System.out.println("going to AdminUsersPage");
			return adminUsersPg;
		case AdminAddUserPage : 
			if (newAdminInfoPg == null)  {
				newAdminInfoPg = new AdminAddUserPage(driver);
			}
			System.out.println("going to AdminAddUserPage");
			return newAdminInfoPg;
		default:
			System.err.println("TopPage.getPage: No PageObject for given page: " +  page);
		}
		return null;
	}

}
