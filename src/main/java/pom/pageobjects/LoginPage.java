package pom.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	private static final Logger logger = LogManager.getLogger(LoginPage.class);
	
	@FindBy(id="user-name")
	WebElement usernameField;
	
	@FindBy(id="password")
	WebElement passwordField;

	@FindBy(id="login-button")
	WebElement loginButton;
	
	@FindBy(xpath="//h3[contains(text(),'Epic sadface: Username and password do not match any user in this service')]")
	WebElement errorMessage;
	
	@FindBy(id="react-burger-menu-btn")
	WebElement burgerMenu;
	
	@FindBy(id="logout_sidebar_link")
	WebElement logoutButton;
	
	
	public void enterUserName(String username) {
		logger.info("Entering the username: "+username);
		usernameField.clear();
		usernameField.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		logger.info("Entering the username: "+password);
		passwordField.clear();
		passwordField.sendKeys(password);
	}
	
	public void clickLoginButton() {
		logger.info("Clicking the login button");
		loginButton.click();
	}
	
	public boolean checkLoggedIn() {
		if(burgerMenu.isDisplayed()) return true;
		else return false;
	}
	
	public void login(String username, String password) {
		logger.info("Entering the username: "+username);
		usernameField.clear();
		usernameField.sendKeys(username);
		logger.info("Entering the username: "+password);
		passwordField.clear();
		passwordField.sendKeys(password);
		logger.info("Clicking the login button");
		loginButton.click();
	}
	
	public boolean checkErrorMessage() {
		if(errorMessage.isDisplayed())return true;
		else return false;
	}
	
	public void logout() {
		logger.info("Clicking burger menu");
		burgerMenu.click();
		logger.info("Clicking Logout button");
		logoutButton.click();
	}

}
