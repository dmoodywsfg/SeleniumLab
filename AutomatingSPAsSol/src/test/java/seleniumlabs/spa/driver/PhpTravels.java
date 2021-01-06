package seleniumlabs.spa.driver;

import org.testng.Assert;
import org.testng.annotations.Test;

import seleniumlabs.spa.pages.AdminAddUserPage;
import seleniumlabs.spa.pages.AdminUsersPage;
import seleniumlabs.spa.pages.DashboardPage;
import seleniumlabs.spa.pages.LoginPage;

public class PhpTravels {
	private LoginPage      loginPage = null;
	private AdminUsersPage adminUsersPg = null;
	private DashboardPage  dashboardPg = null;
	private AdminAddUserPage newAdminInfoPg = null;

	@Test
	public void addAdmin() throws InterruptedException {
		String newUEmail = "bb@yy.com";
		String newUPassword = "password";
		String newUFirstName = "BB";
		String newULastName = "Birze";
		String displayName = newUFirstName + " " + newULastName;
		
		loginPage = new LoginPage();
		Assert.assertTrue(loginPage.automationOK(), "Could Not Instantiate Driver");
		Assert.assertTrue(loginPage.loadPage(), "Could not load admin login page");

		// PHP Travels Login Page
		// ===============================
		dashboardPg =loginPage.login("admin@phptravels.com", "demoadmin");
		Assert.assertTrue(dashboardPg.onPage(), "Could not Login to PHP Travels as Admin");

		// On Dash board Page
		// ===============================
		adminUsersPg = dashboardPg.selectAccountsAdmins();
		Assert.assertTrue(adminUsersPg.onPage(), "Could not get to Admin Users page");

		// On Admin Users Page
		// ==================================
		newAdminInfoPg = adminUsersPg.addAdmin();
		Assert.assertTrue(newAdminInfoPg.onPage(), "Could not get to Add Admin Users page");

		// On Add Admin User Info Page
		// ==================================

		newAdminInfoPg.enterUserCreds(newUFirstName, newULastName, newUEmail, newUPassword);
		newAdminInfoPg.enterUserLocation("(987)678-9767", "United States", "345 Bluebird Lane", "TX");
		
		adminUsersPg = newAdminInfoPg.addNewAdmin(true, false);
		Assert.assertTrue(adminUsersPg.onPage(), "Could return to Admin Users page after add user");
		Assert.assertTrue(adminUsersPg.userExists(newUFirstName, newULastName, newUEmail));
			
		// Log off and log back in with new User ID
		// ==========================================
		loginPage = adminUsersPg.logout();
		Assert.assertTrue(loginPage.onPage(), "Could not get to admin login page");

		dashboardPg =loginPage.login(newUEmail, newUPassword);
		Assert.assertTrue(dashboardPg.onPage(), "Could not Login to PHP Travels as Admin");
		Assert.assertTrue(dashboardPg.userLogedIn(displayName));		// is new yer logged in?
		//loginPage.quit();
	}

}
