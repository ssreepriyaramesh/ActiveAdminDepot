package aws.activeadmindepot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import pageutil.BasePage;

public class NewCustomerDetPage extends BasePage {

	NewCustomerDetPage(WebDriver driver) {
		super(driver);
		logger.info("In the NewCustomerDetPage");
	}

	private By loc_ptxtPageTitle = By.id("page_title");
	private By loc_ptxtFlashNotice = By.xpath("//div[@class='flash flash_notice']");

	public String getHeaderPageTitle() {
		return driver.findElement(loc_ptxtPageTitle).getText();
	}

	public String getFlashNotice() {
		return driver.findElement(loc_ptxtFlashNotice).getText();
	}

}
