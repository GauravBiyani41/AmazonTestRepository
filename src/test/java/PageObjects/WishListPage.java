package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WishListPage {

	WebDriver driver = null;

	By wishListTitle = By.xpath("//div[@role='heading']");
	By wishListProduct = By.xpath("//h2/a");
	By deleteWishListProduct = By.xpath("//input[@name='submit.deleteItem']");
	By deletedProductSuccessfulMessage = By
			.xpath("//div[contains(@id, 'item_')]//div[@class='a-alert-content']");

	public WishListPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getWishListTitle() {
		return driver.findElement(wishListTitle).getText();
	}

	public String getWishListProductName() {
		String productName = driver.findElement(wishListProduct).getText();
		productName = productName.substring(0,7);
		return productName;
	}

	public void clickOnDeleteTheWishListProduct() {
		driver.findElement(deleteWishListProduct).click();
	}

	public String getDeletedWishListMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(deletedProductSuccessfulMessage));
		return driver.findElement(deletedProductSuccessfulMessage).getText();
	}
	
}
