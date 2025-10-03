package pom.pageobjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
	
	private static final Logger logger = LogManager.getLogger(CartPage.class);

	public CartPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="checkout")
	WebElement checkoutButton;
	
	@FindBy(id="continue-shopping")
	WebElement backbutton;
	
	@FindBy(xpath="//button[text()='Remove']")
	WebElement removeButton; // same name for all the remove buttons of the list
	
	@FindBy(xpath="//div[@class='cart_item']")
	List<WebElement> cartItemsList;
	
	//CheckoutPage Elements
	@FindBy(id="first-name")
	WebElement firstNameField;
	
	@FindBy(id="last-name")
	WebElement lastNameField;
	
	@FindBy(id="postal-code")
	WebElement postalCodeField;
	
	@FindBy(id="continue")
	WebElement continueButton;
	
	//overview page
	@FindBy(id="finish")
	WebElement finishButton;
	
	@FindBy(xpath="//h2[@class='complete-header']")
	WebElement verifyOrderMessage;
	
	
	public List<WebElement> getCartItemsList(){
		return cartItemsList;
	}
	
	public void clickRemove() {
		logger.info("Remove Button click");
		removeButton.click();
	}
	
	public boolean isCartEmpty() {
		if(cartItemsList.isEmpty()) return true;
		else return false;
	}
	
	public void clickContinue() {
		logger.info("Continue Button click");
		continueButton.click();
	}
	
	public void clickFinish() {
		logger.info("Finish Button click");
		finishButton.click();
	}
	
	public void enterFirstName() {
		logger.info("enterFirstName");
		firstNameField.clear();
		firstNameField.sendKeys("random");
	}
	
	public void enterLastName() {
		logger.info("enterLastName");
		lastNameField.clear();
		lastNameField.sendKeys("random");
	}
	
	public void enterPostalCode() {
		logger.info("enterPostalCode");
		postalCodeField.clear();
		postalCodeField.sendKeys("random");
	}
	
	public void clickCheckout() {
		logger.info("Checkout Button click");
		checkoutButton.click();
	}
	
	public boolean verifyOrderSubmission() {
		if(verifyOrderMessage.isDisplayed()) return true;
		else return false;
	}

}
