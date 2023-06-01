package PageObjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ResultOfTheSearch {

	WebDriver driver = null;

	public List<WebElement> prices;
	public List<WebElement> getRatings;
	public List<WebElement> getRatingsText;
	public List<WebElement> resultsOfTheRatingsOfTheProducts;

	By fetchedRatingsOfTheProducts = By
			.xpath("//div[contains(@class,'small a-spacing-top-small')]//div[@class='a-row a-size-small']/span[1]");
	By ratings = By.xpath("//div[@id='reviewsRefinements']//span[@data-csa-c-type='element']");
	By ratingsText = By.xpath("//div[@id='reviewsRefinements']//span[@class='a-icon-alt']");
	By enterLowPrice = By.xpath("//input[@id='low-price']");
	By enterMaxPrice = By.xpath("//input[@id='high-price']");
	By clickOnGo = By.xpath("//span[@class='a-button-inner']/input[@type='submit']");
	By pricesOfTheProducts = By.xpath(
			"//div[contains(@class,'a-spacing-top-micro s-price-instructions-style')]//span[@class='a-price-whole']");

	public ResultOfTheSearch(WebDriver driver) {
		this.driver = driver;
	}

	public void fetchedTheRatingsOfTheProducts() {
		resultsOfTheRatingsOfTheProducts = driver.findElements(fetchedRatingsOfTheProducts);
	}

	public void getTheRatingAndClickOnTheRating(int ratingsOfTheProduct) {
		 driver.findElements(ratings).get(4-ratingsOfTheProduct).click();
		 
//		getRatingsText = driver.findElements(ratingsText);
//		for (int i = 0; i < getRatings.size(); i++) {
//			WebElement ratingElement = getRatings.get(i);
//			String getRatingsText1 = getRatingsText.get(i).getAttribute("innerText");
//			getRatingsText1 = getRatingsText1.substring(0, 1);
//			double ratingfetched = Double.parseDouble(getRatingsText1);
//
//			if (ratingsOfTheProduct == ratingfetched) {
//				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//				wait.until(ExpectedConditions.elementToBeClickable(ratingElement)).click();
//				break;
//			}
//		}
	}

	public void enterPrice(String lowPrice, String maxPrice) throws InterruptedException {
		driver.findElement(enterLowPrice).sendKeys(lowPrice);
		driver.findElement(enterMaxPrice).sendKeys(maxPrice);
		Thread.sleep(1000);
		driver.findElement(clickOnGo).click();
	}

	public void getPricesOfTheProducts() {
		prices = driver.findElements(pricesOfTheProducts);
	}
}
