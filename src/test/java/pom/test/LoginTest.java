package pom.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest{
	
	private static final Logger logger = LogManager.getLogger(LoginTest.class);
	
	@Test(priority=0,groups="SMOKE")
	public void validLoginTest() {
		logger.info("----------------------Starting TC001:validLoginTest------------------------");
		login.enterUserName("standard_user");
		login.enterPassword("secret_sauce");
		login.clickLoginButton();
		
		if(login.checkLoggedIn()) {
			logger.info("Test Case Passsed");
			login.logout();
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test Case Failed");
			Assert.assertTrue(false);
		}
		logger.info("----------------------Completed testing-----------------------");
	}
	
	@Test(priority=1,groups="Regression")
	public void invalidLoginTest() {
		logger.info("----------------------Starting TC002:invalidLoginTest------------------------");
		login.enterUserName("standard_user");
		login.enterPassword("secret");
		login.clickLoginButton();
		logger.info("----------------------Completed testing-----------------------");
		Assert.assertTrue(login.checkErrorMessage(),"Error Message is not displayed");
	}

	
}
