package aws.activeadmindepot;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageutil.BasePage;

public class NewUserPage extends BasePage {

	NewUserPage(WebDriver driver) {
		super(driver);
		logger.info("In the NewUserPage");
	}

	private By loc_txtUserName = By.id("user_username");
	private By loc_txtPassword = By.id("user_password");
	private By loc_txtEmail = By.id("user_email");
	private By loc_btnCreateUser = By.xpath("//input[contains(@value,'Create User')]");
	private By loc_ptxtInlineErrs = By.xpath("//p[@class='inline-errors']");

	public Object createNewUser(String strUserName, String strPassword, String strEmail) {
		Object retObj = null;
		WebElement txtUserName = driver.findElement(loc_txtUserName);
		txtUserName.clear();
		txtUserName.sendKeys(strUserName);

		WebElement txtPassword = driver.findElement(loc_txtPassword);
		txtPassword.clear();
		txtPassword.sendKeys(strPassword);

		WebElement txtEmail = driver.findElement(loc_txtEmail);
		txtEmail.clear();
		txtEmail.sendKeys(strEmail);

		driver.findElement(loc_btnCreateUser).submit();

		List<WebElement> lst_ptxtInlineErrs = driver.findElements(loc_ptxtInlineErrs);

		if (lst_ptxtInlineErrs.size() == 0) {
			retObj = new NewCustomerDetPage(driver);
		} else {
			ArrayList<String> alstInlineErrs = new ArrayList<String>();
			for (WebElement we : lst_ptxtInlineErrs) {
				alstInlineErrs.add(we.getText());
			}

			retObj = alstInlineErrs;
		}

		return retObj;
	}

}
