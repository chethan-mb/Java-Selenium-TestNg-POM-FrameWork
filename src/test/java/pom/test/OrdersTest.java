package pom.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrdersTest extends BaseTest {
	
	private static final Logger logger = LogManager.getLogger(OrdersTest.class);
	
	@Test(groups="SMOKE")
	public void orderOneItem() {
		logger.info("----------------------------------Starting TC003 Order oner item------------------------------------");
		logger.info("Login");
		login.login("standard_user", "secret_sauce");
		
		List<WebElement> productList = products.getItemsList();
		
		products.clickAddToCartbutton(productList.get(0));
		products.clickOnCart();
		cart.clickCheckout();
		cart.enterFirstName();
		cart.enterLastName();
		cart.enterPostalCode();
		cart.clickContinue();
		cart.clickFinish();
		
		if(cart.verifyOrderSubmission()) {
			logger.info("Test Case passed");
			Assert.assertTrue(true);
		}
		else {
			logger.info("Test Case falied");
			Assert.assertTrue(false);
		}
		logger.info("----------------------------------Completed testing------------------------------------------------");
	}
}
