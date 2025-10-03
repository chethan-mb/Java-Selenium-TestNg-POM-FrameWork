package pom.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import pom.pageobjects.CartPage;
import pom.pageobjects.LoginPage;
import pom.pageobjects.ProductsPage;
import pom.utilis.ConfigReader;

public class BaseTest {
	
	protected WebDriver driver;
	protected Properties config;
	protected LoginPage login;
	protected ProductsPage products;
	protected CartPage cart;
	private static final Logger logger = LogManager.getLogger(BaseTest.class);

	
	@BeforeClass(alwaysRun = true)
    public void setup() throws MalformedURLException {
        config = ConfigReader.getProperties();
        String execution = config.getProperty("execution");
        String browser = config.getProperty("browser");
        String hubUrl = config.getProperty("hubUrl");
        String url = config.getProperty("url");

        if (execution.equalsIgnoreCase("local")) {
            // Local execution
            if (browser.equalsIgnoreCase("chrome")) {
            	ChromeOptions options = new ChromeOptions();
            	options.addArguments("--ignore-certificate-errors");
            	options.addArguments("--disable-save-password-bubble");
            	options.addArguments("--disable-notifications"); // Didnt work on chrome password leak popup
            	Map<String, Object> prefs = new HashMap<String, Object>();
            	prefs.put("credentials_enable_service", false);
            	prefs.put("profile.password_manager_enabled", false);
            	prefs.put("profile.password_manager_leak_detection", false);// Worked on chrome password leak popuo
            	options.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(options);
            } else if (browser.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            }
            
        } else if (execution.equalsIgnoreCase("remote")) {
            // Remote execution (Selenium Grid / Cloud)
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName(browser);
            driver = new RemoteWebDriver(new URL(hubUrl), caps);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);
        login = new LoginPage(driver);
        products = new ProductsPage(driver);
        cart = new CartPage(driver);
        
        
        logger.info("Opened "+browser+" with URL: " + url);
    }
	
//	@BeforeMethod()
//	public void setupPageObjects() {
//		login = new LoginPage(driver);
//	}

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
