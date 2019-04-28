package aws.activeadmindepot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pageutil.BasePage;

public class MainDashBrdPage extends BasePage {

	MainDashBrdPage(WebDriver driver) {
		super(driver);
		logger.info("In the MainDashBrdPage");
	}

	private By loc_lnkUsers = By.id("users");

	public MainUsersPage goToUsersPage() {
		driver.findElement(loc_lnkUsers).click();
		return new MainUsersPage(driver);
	}

}
