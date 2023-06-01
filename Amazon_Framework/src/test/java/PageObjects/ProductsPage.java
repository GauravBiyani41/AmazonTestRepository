package PageObjects;

import java.util.Iterator;

import java.util.Set;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;


public class ProductsPage {

	WebDriver driver = null;

	By results = By.xpath("//div[contains(@class,'a-spacing-top-small')]/span[contains(@class,'a-text-bold')]");
	By clickOnFirstProduct = By.xpath("//*[@id='search']//h2/a[1]");
	By productDescription = By.xpath("//a[@id='seeMoreDetailsLink']");
	By addToCart = By.xpath("//*[@id='add-to-cart-button']");
	By clickOnCart = By.xpath("//*[@id='attach-sidesheet-view-cart-button']/span/input");
	By cartTitle = By.xpath("//h1");
	By productInCart = By.xpath("//span[@class='a-truncate-cut']");
	By buyProduct = By.xpath("//*[@id='sc-buy-box-ptc-button']/span/input");
	By deliveryAddressTitle = By.xpath("//div[@class='a-column a-span10']/h3[@class='a-color-state']");
	By deleteTheProduct = By.xpath("//input[@value='Delete']");
	By deletedProductMessage = By.xpath("//div[@class='a-row']/h1");

	public ProductsPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnTheProduct() {
		driver.findElement(clickOnFirstProduct).click();
	}

	//switch to the child window from one window to another window
	public void switchToProductDescriptionWindow() {

		Set<String> windowHandles = driver.getWindowHandles();
		Iterator windowItr = windowHandles.iterator();
		String productWindow = (String) windowItr.next();
		String productDescriptionWindow = (String) windowItr.next();
		driver.switchTo().window(productDescriptionWindow);

	}

	//removing the Quoation "" from the string
	public String verifyResultsofTheSearch() {
		String searchResult = driver.findElement(results).getText();
		searchResult = searchResult.replaceAll("\"", "");	
		return searchResult;
	}

	public void clickOnAddToCart() {

		driver.findElement(addToCart).click();
		driver.findElement(clickOnCart).click();
	}

	public String verifyProductDescription() {
		return driver.findElement(productDescription).getText();
	}

	public String verifyCartTitle() {
		return driver.findElement(cartTitle).getText();
	}

	//fetching product name present in the cart
	public String verifyProductInCart() {
		String productName = driver.findElement(productInCart).getText();
		productName = productName.substring(0,7);
		return productName;
	}

	public void clickOnBuyProduct() {
		driver.findElement(buyProduct).click();
	}

	//fetching the shipping address title from the delivery address title pages
	public String getAddressPageTitle() {
		String addressPageTitle= driver.findElement(deliveryAddressTitle).getText();
		addressPageTitle = addressPageTitle.replaceAll("[^ a-zA-Z]","");
		return addressPageTitle;
	}

	public void backAndDeleteProductFromCart() {
		driver.navigate().back();
		driver.findElement(deleteTheProduct).click();

	}

	// tried added explicit wait but was not working so added thread.sleep()
	public String deleteProductMessage() throws InterruptedException {
		Thread.sleep(5000);
		return driver.findElement(deletedProductMessage).getText();
	}
}
