package testutil;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestBaseClass {

	protected static WebDriver driver;
	
	protected Logger logger;

	@Parameters({ "browserType" }) 
	@BeforeClass(alwaysRun = true)
	public void setup(String browser) throws Exception {
		
		logger = Logger.getLogger("TestBaseClass");
        PropertyConfigurator.configure("log4j.properties");
        logger.info("In the TestBaseClass");
        
		if (browser.equals("FF")) {
			System.setProperty("webdriver.gecko.driver",
			System.getProperty("user.dir") + "//src//test//resources//geckodriver.exe");
			driver = new FirefoxDriver();
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
		} else if (browser.equals("CH")) {
			
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//src//test//resources//chromedriver.exe");
			driver = new ChromeDriver();
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
		} else {
			throw new Exception("Browser is not correct");
		}
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		driver = null;
	}
}
