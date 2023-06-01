package PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage {

	WebDriver driver = null;
	public List<WebElement> differentOptions;
	public List<WebElement> numberOfAddressesSize;

	By homePageTitleVerification = By.xpath("//*[@id='nav-logo-sprites']");
	By validCredentialLandedtoHomePage = By.xpath("//span[text()='Hello, Gaurav']");
	By allTheOptions = By.xpath("//a[@id='nav-hamburger-menu']/span[text()='All']");
	By signOut = By.xpath("//a[text()='Sign Out']");
	By yourAccount = By.xpath("//a[@class='hmenu-item' and text()='Your Account']");
	By yourAddresses = By.xpath("//div[@data-card-identifier='AddressesAnd1Click']//h2");
	By optionsUnderSearch = By.xpath("//div[@id='nav-xshop']/a[@class='nav-a  ']");
	By searchField = By.xpath("//input[@id='twotabsearchtextbox']");
	By hoverToAddressChange = By.xpath("//*[@id='nav-global-location-popover-link']");
	By defaultAddressText = By.xpath("//span[@id='glow-ingress-line2']");
	By selectAddress1 = By.xpath("//ul[@id='GLUXAddressList']/li[1]//input");
	By selectAddress2 = By.xpath("//ul[@id='GLUXAddressList']/li[2]//input");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	public String amazonHomePageTitle() {
		return driver.findElement(homePageTitleVerification).getAttribute("aria-label");
	}

	public String userNameOnHomePage() {
		return driver.findElement(validCredentialLandedtoHomePage).getText();
	}

	public void clickOnAll() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(allTheOptions));
		driver.findElement(allTheOptions).click();
	}

	// used java script executor for scroll down to click on your account
	public void clickOnYourAccount() {

		WebElement userYourAccount = driver.findElement(yourAccount);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", userYourAccount);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(userYourAccount));

		userYourAccount.click();

	}

	public void clickOnSignOut() {

		WebElement userSignOut = driver.findElement(signOut);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", userSignOut);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(userSignOut));

		userSignOut.click();
	}

	public void clickOnyourAddresses() {
		driver.findElement(yourAddresses).click();
	}

	// fetching the different options under the search for validating the different
	// options
	// available or not under the search bar
	
	public void differentOptionsunderSearch() {
		differentOptions = driver.findElements(optionsUnderSearch);
	}

	public void enterDataIntoTheSearchField(String enterData) {
		driver.findElement(searchField).sendKeys(enterData);
		driver.findElement(searchField).sendKeys(Keys.ENTER);
	}

	// clicking on the address to be changed on the homepage
	public void changeUserAddressOnHomePage() {
		driver.findElement(hoverToAddressChange).click();
	}

	// Changing the address on the homepage
	public void clickOnDifferentAddress1() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(selectAddress1)).click();
	}

	// Changing the address on the homepage
	public void clickOnDifferentAddress2() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(selectAddress2)).click();
	}

	// fetching the address text from homepage which is currently available before
	// the address is got changed and thread.sleep is needed to use as explicit wait and
	// implicit wait is not working for fetching the current address text present on
	// the home page, here replace all is used to remove the unwanted characters generated
	
	public String getDefaultAddressText() throws InterruptedException {
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String address = wait.until(ExpectedConditions.visibilityOfElementLocated(defaultAddressText)).getText();
		address = address.replaceAll("[^ a-zA-Z0-9]", "");
		return address;
	}

	
	// if the fetched string contains the defaultaddress1 text then shift to the new
	// address if the fetched string contains the defaultaddress2 text then shift to the new
	// address if there is nothing reflected on the homepage related to the address then
	// shift to the defaultaddress1,here replace all is used to remove the unwanted characters
	// generated
	
	public void changeAddress(String fetchedString, String defaultAddress1, String defaultAddress2,
			String checkAddress2, String checkingAddress1) throws InterruptedException {
		if (fetchedString.contains(defaultAddress1)) {
			clickOnDifferentAddress2();
			checkAddress2=checkAddress2.replaceAll("[^ a-zA-Z0-9]", "");
			Assert.assertEquals(checkAddress2, getDefaultAddressText());
		} else if (fetchedString.contains(defaultAddress2)) {
			clickOnDifferentAddress1();
			checkingAddress1 = checkingAddress1.replaceAll("[^ a-zA-Z0-9]", "");
			Assert.assertEquals(checkingAddress1, getDefaultAddressText());
		}

		else {
			clickOnDifferentAddress1();
			checkingAddress1 = checkingAddress1.replaceAll("[^ a-zA-Z0-9]", "");
			Assert.assertEquals(checkingAddress1, getDefaultAddressText());
		}
	}
}
