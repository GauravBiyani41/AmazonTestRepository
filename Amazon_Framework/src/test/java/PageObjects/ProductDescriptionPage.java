package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDescriptionPage {

	WebDriver driver = null;

	By addProductToWishList = By.xpath("//input[@id='add-to-wishlist-button-submit']");

	By viewYourWishList = By.xpath("//span[@id='huc-view-your-list-button']/span/a");

	public ProductDescriptionPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnWishList() {
		driver.findElement(addProductToWishList).click();
	}

	public void clickOnViewYourList() {
		driver.findElement(viewYourWishList).click();
	}
}
