package aws.activeadmindepot;

import java.util.ArrayList;
import java.util.Random;

import org.testng.annotations.Test;

import testutil.CustomSoftAssert;
import testutil.TestBaseClass;

public class TC001_NewUserScreen extends TestBaseClass {

	CustomSoftAssert csa = new CustomSoftAssert();

	@Test(priority = 1, description = "verify New User Screen with valid data", groups = { "functional testing" })
	public void verifyWithValidData() {
		logger.info("In the TC001_NewUserScreen Class - verifyWithValidData()");
		driver.get("http://ec2-54-84-52-184.compute-1.amazonaws.com:8080/admin/");

		MainDashBrdPage mnDashBrdPg = new MainDashBrdPage(driver);

		MainUsersPage mnUsersPg = mnDashBrdPg.goToUsersPage();
		NewUserPage nwUserPg = mnUsersPg.goToNewUserPage();

		Random rdm = new Random();
		String strUserName = "User" + rdm.nextInt(99999);
		String strPassword = "password";
		String strEmail = strUserName + "@aws123.net";

		NewCustomerDetPage nwCustDetPg = (NewCustomerDetPage) nwUserPg.createNewUser(strUserName, strPassword,
				strEmail);
		String strActHeadPgTitle = nwCustDetPg.getHeaderPageTitle();
		logger.info("The Actual Header Page Title: " + strActHeadPgTitle);
		csa.assertEquals(strActHeadPgTitle, strUserName);
		csa.assertAll();

		String strActFlNotice = nwCustDetPg.getFlashNotice();
		String strExpFlNotice = "User was successfully created.";
		logger.info("The Actual Create User Message: " + strActFlNotice);
		csa.assertEquals(strActFlNotice, strExpFlNotice);
		csa.assertAll();
	}

	@Test(priority = 2, description = "verify New User Screen with invalid data", groups = { "functional testing" })
	public void verifyWithInValidData() {

		logger.info("In the TC001_NewUserScreen Class - verifyWithInValidData()");
		driver.get("http://ec2-54-84-52-184.compute-1.amazonaws.com:8080/admin/");

		MainDashBrdPage mnDashBrdPg = new MainDashBrdPage(driver);

		MainUsersPage mnUsersPg = mnDashBrdPg.goToUsersPage();
		NewUserPage nwUserPg = mnUsersPg.goToNewUserPage();

		String strUserName = "User%$^&";
		String strPassword = "pas";
		String strEmail = strUserName + "@aws123.net";

		ArrayList<String> alstInlineErrs = (ArrayList<String>) nwUserPg.createNewUser(strUserName, strPassword,
				strEmail);

		for (String strInlineErrs : alstInlineErrs) {
			csa.assertNotNull(strInlineErrs);
			csa.assertAll();
			logger.info("Invalid Data - Error message: " + strInlineErrs);
		}

	}

	@Test(priority = 3, description = "verify New User Screen with blank data", groups = { "functional testing" })
	public void verifyWithBlankData() {
		logger.info("In the TC001_NewUserScreen Class - verifyWithBlankData()");

		driver.get("http://ec2-54-84-52-184.compute-1.amazonaws.com:8080/admin/");

		MainDashBrdPage mnDashBrdPg = new MainDashBrdPage(driver);

		MainUsersPage mnUsersPg = mnDashBrdPg.goToUsersPage();
		NewUserPage nwUserPg = mnUsersPg.goToNewUserPage();

		String strUserName = "";
		String strPassword = "";
		String strEmail = "";

		ArrayList<String> alstInlineErrs = (ArrayList<String>) nwUserPg.createNewUser(strUserName, strPassword,
				strEmail);

		for (String strInlineErrs : alstInlineErrs) {
			csa.assertNotNull(strInlineErrs);
			csa.assertAll();
			logger.info("Blank Data - Error message: " + strInlineErrs);
		}
	}
}
