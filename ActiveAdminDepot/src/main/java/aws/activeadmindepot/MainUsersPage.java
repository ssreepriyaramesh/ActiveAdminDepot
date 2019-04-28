package aws.activeadmindepot;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import pageutil.BasePage;

public class MainUsersPage extends BasePage {

	MainUsersPage(WebDriver driver) {
		super(driver);
		logger.info("In the MainUsersPage");
	}

	private By loc_lnkNewUser = By.xpath("//a[contains(text(),'New User')]");

	private By loc_selFiltUsername = By.xpath("//div[@id='q_username_input']/child::select");
	private By loc_txtFiltUsername = By.id("q_username");
	private By loc_selFiltUserEmail = By.xpath("//div[@id='q_email_input']/child::select");
	private By loc_txtFiltUserEmail = By.id("q_email");
	private By loc_dpFiltFromDate = By.id("q_created_at_gteq_datetime");
	private By loc_dpFiltToDate = By.id("q_created_at_lteq_datetime");
	private By loc_btnFilter = By.xpath("//input[@value='Filter']");
	private By loc_tblUsers = By.id("index_table_users");
	private By loc_tblcolUsername = By
			.xpath("//table[@id='index_table_users']/tbody/tr/td[contains(@class, 'col col-username')]");
	private By loc_tblcolEmail = By
			.xpath("//table[@id='index_table_users']/tbody/tr/td[contains(@class, 'col col-email')]");
	private By loc_tblcolCreatedAtDate = By
			.xpath("//table[@id='index_table_users']/tbody/tr/td[contains(@class, 'col col-created_at')]");
	private By loc_navPageList = By.xpath("//nav[@class='pagination']/child::span[contains(@class,'page')]");

	private WebElement tblUsers = null;

	public NewUserPage goToNewUserPage() {
		driver.findElement(loc_lnkNewUser).click();
		return new NewUserPage(driver);
	}

	public void setFilterUserName(String strFiltUNOption, String strFiltUNText) {
		Select selFiltUsername = new Select(driver.findElement(loc_selFiltUsername));
		selFiltUsername.selectByVisibleText(strFiltUNOption);

		WebElement txtFiltUsername = driver.findElement(loc_txtFiltUsername);
		txtFiltUsername.clear();
		txtFiltUsername.sendKeys(strFiltUNText);
	}

	public void setFilterUserEmail(String strFilterUserEmailOpt, String strFilterUserEmailText) {
		Select selFiltUserEmail = new Select(driver.findElement(loc_selFiltUserEmail));
		selFiltUserEmail.selectByVisibleText(strFilterUserEmailOpt);

		WebElement txtFiltUserEmail = driver.findElement(loc_txtFiltUserEmail);
		txtFiltUserEmail.clear();
		txtFiltUserEmail.sendKeys(strFilterUserEmailText);
	}

	public void setFilterUserDates(String strFilterUsrFromDate, String strFilterUsrToDate) {
		driver.findElement(loc_dpFiltFromDate).sendKeys(strFilterUsrFromDate);
		driver.findElement(loc_dpFiltToDate).sendKeys(strFilterUsrToDate);

	}

	public void filterUsers() {
		tblUsers = null;

		driver.findElement(loc_btnFilter).click();
		tblUsers = driver.findElement(loc_tblUsers);
	}

	public ArrayList getDataByUserName() {
		List<WebElement> pagesList = driver.findElements(loc_navPageList);
		tblUsers = driver.findElement(loc_tblUsers);
		List<WebElement> lst_colUsernames = tblUsers.findElements(loc_tblcolUsername);

		ArrayList<String> alstUsernames = new ArrayList<String>();

		for (int i = 0; i <= pagesList.size(); i++) {
			pagesList = driver.findElements(loc_navPageList);
			if (pagesList.size() > 0 && i < pagesList.size()) {
				pagesList.get(i).click();
			}
			tblUsers = driver.findElement(loc_tblUsers);
			lst_colUsernames = tblUsers.findElements(loc_tblcolUsername);

			for (WebElement we : lst_colUsernames) {
				String strUserName = we.getText();
				alstUsernames.add(strUserName);
			}

			if (i == (pagesList.size() - 1)) {
				break;
			}
		}

		return alstUsernames;
	}

	public ArrayList getDataByUserEmail() {
		List<WebElement> pagesList = driver.findElements(loc_navPageList);
		tblUsers = driver.findElement(loc_tblUsers);
		List<WebElement> lst_colUserEmails = tblUsers.findElements(loc_tblcolEmail);

		ArrayList<String> alstUserEmails = new ArrayList<String>();

		for (int i = 0; i <= pagesList.size(); i++) {
			pagesList = driver.findElements(loc_navPageList);
			if (pagesList.size() > 0 && i < pagesList.size()) {
				pagesList.get(i).click();
			}
			tblUsers = driver.findElement(loc_tblUsers);
			lst_colUserEmails = tblUsers.findElements(loc_tblcolEmail);

			for (WebElement we : lst_colUserEmails) {
				String strUserEmail = we.getText();
				alstUserEmails.add(strUserEmail);
			}

			if (i == (pagesList.size() - 1)) {
				break;
			}
		}

		return alstUserEmails;
	}

	public ArrayList getDataByCreatedAtDates() {
		List<WebElement> pagesList = driver.findElements(loc_navPageList);
		tblUsers = driver.findElement(loc_tblUsers);
		List<WebElement> lst_colUserDates = tblUsers.findElements(loc_tblcolCreatedAtDate);

		ArrayList<String> alstUserDates = new ArrayList<String>();

		for (int i = 0; i <= pagesList.size(); i++) {
			pagesList = driver.findElements(loc_navPageList);
			if (pagesList.size() > 0 && i < pagesList.size()) {
				pagesList.get(i).click();
			}

			tblUsers = driver.findElement(loc_tblUsers);
			lst_colUserDates = tblUsers.findElements(loc_tblcolCreatedAtDate);

			for (WebElement we : lst_colUserDates) {
				String strUserDate = we.getText();
				alstUserDates.add(strUserDate);
			}

			if (i == (pagesList.size() - 1)) {
				break;
			}
		}

		return alstUserDates;
	}

}
