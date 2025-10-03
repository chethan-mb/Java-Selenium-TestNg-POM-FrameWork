package pom.pageobjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ProductsPage extends BasePage {
	
	private static final Logger logger = LogManager.getLogger(ProductsPage.class);

	public ProductsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="react-burger-menu-btn")
	WebElement burgerMenu;
	
	@FindBy(xpath="//a[@class='shopping_cart_link']")
	WebElement cart;
	
	@FindBy(xpath="//div[@class='inventory_list']")
	WebElement itemsListWrapper; //all items list wrapper class
	
	@FindBy(xpath="//div[@class='inventory_item']")
	List<WebElement> singleItems; //Same class name for all the list items
	
	@FindBy(xpath="//button[text()='Add to cart']")
	WebElement addToCartButton;
	
	@FindBy(xpath="//select[@class='product_sort_container']")
	WebElement sortOptions;
	
	
	public List<WebElement> getItemsList() {
		return singleItems;
	}
	
	public void clickOnCart() {
		logger.info("Cart Button Click");
		cart.click();
	}
	
	public void clickAddToCartbutton(WebElement wrapperElement) {
		logger.info("Add to Cart Button Click");
		wrapperElement.findElement(By.xpath("//button[text()='Add to cart']")).click();
	}
	
	
	
}
