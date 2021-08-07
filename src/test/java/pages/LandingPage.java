package pages;

import common.DriverFactory;
import common.EventListeners;
import common.SafeActions;
import common.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LandingPage extends SafeActions {
	@FindBy(css = "input[name='search']")
	private WebElement searchBox;

	@FindBy(how = How.CSS, using = "div[id='search'] button")
	private WebElement searchButton;

	@FindBy(css = "div[class='product-thumb']")
	@CacheLookup
	private List<WebElement> productThumbs;

	@FindBy(css = "div[class='caption'] a")
	private WebElement productName;

	@FindBy(xpath = "//button[span[.='Add to Cart']]")
	private WebElement addToCartButton;

	@FindBy(xpath = "//a[.='shopping cart']")
	private WebElement shippingCartLink;

	public LandingPage() {
		PageFactory.initElements(DriverFactory.getDriver(),this);
	}

	public void searchForProduct(String productName) {
		EventListeners.childTest.info("Searching for the Product: "+productName);
		sendKeys(searchBox, productName);
		LandingPage.click(searchButton);
		
	}
	
	public void selectRandomProduct() {
		LandingPage.click(productThumbs.get(Utils.getRandomInteger(productThumbs.size())));
	}

}
