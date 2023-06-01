package PageObjects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserAddressPage {

	WebDriver driver = null;
	By fullName = By.xpath("//input[@id='address-ui-widgets-enterAddressFullName']");
	By mobileNo = By.xpath("//input[@id='address-ui-widgets-enterAddressPhoneNumber']");
	By pinCode = By.xpath("//input[@id='address-ui-widgets-enterAddressPostalCode']");
	By AddressLine1 = By.xpath("//input[@id='address-ui-widgets-enterAddressLine1']");
	By AddressLine2 = By.xpath("//input[@id='address-ui-widgets-enterAddressLine2']");
	By AddressLine3 = By.xpath("//input[@id='address-ui-widgets-landmark']");
	By defaultAddress = By.xpath("//input[@id='address-ui-widgets-use-as-my-default']");
	By addAddressButton = By.xpath("//span[@id='address-ui-widgets-form-submit-button']//input[@type='submit']");
	By addressSaved = By.xpath("//h4[text()='Address saved']");
	By addressRemove = By.xpath("//a[@class='a-link-normal delete-link' and text()='Remove']");
	By plusaddressicon = By.xpath("//div[@id='ya-myab-plus-address-icon']");
	By addressDeletedMessage = By.xpath("//h4");

	public UserAddressPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUserFullName(String userFullName) {
		driver.findElement(fullName).sendKeys(userFullName);
	}

	public void enterUserMobileNo(String phoneNumber) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(mobileNo));
		driver.findElement(mobileNo).sendKeys(phoneNumber);
	}

	public void enterUserPinCode(String userpinCode) throws InterruptedException {
		WebElement enterPincode = driver.findElement(pinCode);
		enterPincode.clear();
		enterPincode.sendKeys(userpinCode);

	}


	public void enterUserAddress(String userFullName, String phoneNumber, String userpinCode, String enterAddressLine1,
			String enterAddressLine2, String enterAddressLine3) throws InterruptedException {

		clickOnPlusIcon();
		enterUserFullName(userFullName);
		enterUserMobileNo(phoneNumber);
		enterUserPinCode(userpinCode);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(AddressLine1));

		driver.findElement(AddressLine1).sendKeys(enterAddressLine1);
		driver.findElement(AddressLine2).sendKeys(enterAddressLine2);
		driver.findElement(AddressLine3).sendKeys(enterAddressLine3);

		clickOnDefaultAddress();
		clickOnAddAddressButton();
		addedAddressSaved();
	}

	public void clickOnDefaultAddress() {
		driver.findElement(defaultAddress).click();
	}

	public void clickOnAddAddressButton() {
		driver.findElement(addAddressButton).click();
	}

	public String addedAddressSaved() {
		return driver.findElement(addressSaved).getText();
	}

	// first clicking on the delete the entered address and designed the locators
	// for the clicking on the confirmed delete for the address using explicit wait
	// and thread.sleep as explicit wait and thread.sleep individual not able to click
	//  on the confirm delete and used the actions class
	public void removeAddress() throws InterruptedException {
		driver.findElement(addressRemove).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement addressremoveConfirmation = driver
				.findElement(By.xpath("//*[@id='deleteAddressModal-0-submit-btn']//span[text()='Yes']"));
		Actions act = new Actions(driver);
		wait.until(ExpectedConditions.elementToBeClickable(addressremoveConfirmation));
		Thread.sleep(5000);
		act.moveToElement(addressremoveConfirmation).click().perform();
	}

	public void clickOnPlusIcon() {

		driver.findElement(plusaddressicon).click();
	}

	public String addressDeletedMessage() {
		return driver.findElement(addressDeletedMessage).getText();
	}

}
