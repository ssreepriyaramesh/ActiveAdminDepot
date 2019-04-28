package aws.activeadmindepot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.Test;

import testutil.CustomSoftAssert;
import testutil.TestBaseClass;

public class TC002_UsersFilters extends TestBaseClass {

	CustomSoftAssert csa = new CustomSoftAssert();

	@Test(priority = 1, description = "verify username filter", groups = { "functional testing" }, enabled = true)
	public void verifyUsernameFilter() {
		logger.info("In the TC002_UsersFilters Class - verifyUsernameFilter");
		driver.get("http://ec2-54-84-52-184.compute-1.amazonaws.com:8080/admin/");

		MainDashBrdPage mnDashBrdPg = new MainDashBrdPage(driver);
		MainUsersPage mnUsersPg = mnDashBrdPg.goToUsersPage();

		String strFilterUsernameOpt = "Contains";
		String strFilterUsernameText = "a";
		mnUsersPg.setFilterUserName(strFilterUsernameOpt, strFilterUsernameText);
		mnUsersPg.filterUsers();
		ArrayList alstUserNames = mnUsersPg.getDataByUserName();
		boolean bValidUsernames = validateUsername(alstUserNames, strFilterUsernameOpt, strFilterUsernameText);
		csa.assertTrue(bValidUsernames);
		csa.assertAll();
	}

	boolean validateUsername(ArrayList<String> alstUserNames, String strFiltUsernameBy, String strFiltUsernameTxt) {
		boolean bValidUsernames = true;
		logger.info("The Num of Filter Data Usernames Rows: " + (alstUserNames.size()));
		if (strFiltUsernameBy.equals("Contains")) {
			for (String strUsername : alstUserNames) {
				logger.info("The Filter Data User names are: " + strUsername);
				if (!strUsername.toLowerCase().contains(strFiltUsernameTxt.toLowerCase())) {
					bValidUsernames = false;
					logger.info("The Filter Data On Usernames Failed: " + strUsername);
					break;
				}
			}
		}

		return bValidUsernames;
	}

	@Test(priority = 2, description = "verify email filter", groups = { "functional testing" }, enabled = true)
	public void verifyUserEmailFilter() {

		logger.info("In the TC002_UsersFilters Class - verifyUserEmailFilter()");
		driver.get("http://ec2-54-84-52-184.compute-1.amazonaws.com:8080/admin/");

		MainDashBrdPage mnDashBrdPg = new MainDashBrdPage(driver);
		MainUsersPage mnUsersPg = mnDashBrdPg.goToUsersPage();

		String strFilterUserEmailOpt = "Contains";
		String strFilterUserEmailText = ".net";
		mnUsersPg.setFilterUserEmail(strFilterUserEmailOpt, strFilterUserEmailText);
		mnUsersPg.filterUsers();
		ArrayList alstUserEmails = mnUsersPg.getDataByUserEmail();
		boolean bValidUserEmails = validateUserEmail(alstUserEmails, strFilterUserEmailOpt, strFilterUserEmailText);
		csa.assertTrue(bValidUserEmails);
		csa.assertAll();
	}

	boolean validateUserEmail(ArrayList<String> alstUserEmails, String strFilterUserEmailOpt,
			String strFilterUserEmailText) {
		boolean bValidUserEmails = true;
		logger.info("The Num of Filter Data UserEmail Rows: " + (alstUserEmails.size()));
		if (strFilterUserEmailOpt.equals("Contains")) {
			for (String strUserEmail : alstUserEmails) {
				logger.info("The Filter Data User Emails are: " + strUserEmail);
				if (!strUserEmail.toLowerCase().contains(strFilterUserEmailText.toLowerCase())) {
					bValidUserEmails = false;
					logger.info("The Filter Data On Usernames Failed: " + strUserEmail);
					break;
				}
			}
		}

		return bValidUserEmails;
	}

	@Test(priority = 3, description = "verify date filter", groups = { "functional testing" }, enabled = true)
	public void verifyUsersDateFilter() throws ParseException {
		logger.info("In the TC002_UsersFilters Class - verifyUsersDateFilter()");
		driver.get("http://ec2-54-84-52-184.compute-1.amazonaws.com:8080/admin/");

		MainDashBrdPage mnDashBrdPg = new MainDashBrdPage(driver);
		MainUsersPage mnUsersPg = mnDashBrdPg.goToUsersPage();

		String strFilterUsrFromDate = "2019-04-23";
		String strFilterUsrToDate = "2019-04-26";
		mnUsersPg.setFilterUserDates(strFilterUsrFromDate, strFilterUsrToDate);
		mnUsersPg.filterUsers();
		ArrayList alstUserDates = mnUsersPg.getDataByCreatedAtDates();
		boolean bValidUserDates = validateUserDates(alstUserDates, strFilterUsrFromDate, strFilterUsrToDate);
		csa.assertTrue(bValidUserDates);
		csa.assertAll();

	}

	boolean validateUserDates(ArrayList<String> alstUserDates, String strFilterUsrFromDate, String strFilterUsrToDate)
			throws ParseException {

		boolean bValidUserDates = true;
		logger.info("The Num of Filter Data UserDates Rows: " + (alstUserDates.size()));

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

		Date dtFromDate = sdf1.parse(strFilterUsrFromDate);
		cal.setTime(dtFromDate);
		cal.add(Calendar.DATE, -1);
		dtFromDate = cal.getTime();

		Date dtToDate = sdf1.parse(strFilterUsrToDate);
		cal.setTime(dtToDate);
		cal.add(Calendar.DATE, 1);
		dtToDate = cal.getTime();

		for (String strUserDate : alstUserDates) {
			logger.info("The Filter Data User Dates are: " + strUserDate);

			SimpleDateFormat sdf2 = new SimpleDateFormat("MMMM dd, yyyy HH:mm");
			Date dtCreatedAtDate = sdf2.parse(strUserDate);
			String strdtCreatedAtDate = sdf1.format(dtCreatedAtDate);
			Date dt_CreatedAtDate = sdf1.parse(strdtCreatedAtDate);

			if (!dt_CreatedAtDate.after(dtFromDate) && dt_CreatedAtDate.before(dtToDate)) {
				logger.info("The Filter Data for User Dates Failed: " + dtCreatedAtDate);
				bValidUserDates = false;
				break;
			}
		}

		return bValidUserDates;
	}

	@Test(priority = 4, description = "verify all filters", groups = { "functional testing" }, enabled = true)
	public void verifyUsersAllFilter() throws ParseException {

		logger.info("In the TC002_UsersFilters Class - verifyUsersAllFilter()");
		driver.get("http://ec2-54-84-52-184.compute-1.amazonaws.com:8080/admin/");

		MainDashBrdPage mnDashBrdPg = new MainDashBrdPage(driver);
		MainUsersPage mnUsersPg = mnDashBrdPg.goToUsersPage();

		String strFilterUsernameOpt = "Contains";
		String strFilterUsernameText = "u";
		mnUsersPg.setFilterUserName(strFilterUsernameOpt, strFilterUsernameText);

		String strFilterUserEmailOpt = "Contains";
		String strFilterUserEmailText = ".com";
		mnUsersPg.setFilterUserEmail(strFilterUserEmailOpt, strFilterUserEmailText);

		String strFilterUsrFromDate = "2019-04-20";
		String strFilterUsrToDate = "2019-04-25";
		mnUsersPg.setFilterUserDates(strFilterUsrFromDate, strFilterUsrToDate);

		mnUsersPg.filterUsers();

		ArrayList alstUserNames = mnUsersPg.getDataByUserName();
		boolean bValidUsernames = validateUsername(alstUserNames, strFilterUsernameOpt, strFilterUsernameText);
		csa.assertTrue(bValidUsernames);
		csa.assertAll();

		ArrayList alstUserEmails = mnUsersPg.getDataByUserEmail();
		boolean bValidUserEmails = validateUserEmail(alstUserEmails, strFilterUserEmailOpt, strFilterUserEmailText);
		csa.assertTrue(bValidUserEmails);
		csa.assertAll();

		ArrayList alstUserDates = mnUsersPg.getDataByCreatedAtDates();
		boolean bValidUserDates = validateUserDates(alstUserDates, strFilterUsrFromDate, strFilterUsrToDate);
		csa.assertTrue(bValidUserDates);
		csa.assertAll();
	}

}
