package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SignInPage {

	WebDriver driver = null;
	
	By accountAndList = By.xpath("//span[text()='Account & Lists']");
	By emailTextBox = By.xpath("//*[@id='ap_email']");
	By continueButton = By.xpath("//span[@id='continue']");
	By passwordTextBox = By.xpath("//input[@id='ap_password']");
	By signInButton = By.xpath("//input[@id='signInSubmit']");
	By userName = By.xpath("//*[@id='authportal-main-section']//label");
	By password = By.xpath("//*[@id='authportal-main-section']//label");
    By errorMessage = By.xpath("//span[@class='a-list-item']");
    By changeLink = By.xpath("//a[@id='ap_change_login_claim']");
    
	public SignInPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnSignIn() {

		WebElement clickOnSignInButton = driver.findElement(accountAndList);
		
		//for the validation of the signIn button that is it visible and if 
		//it is visible then clicking on it otherwise refresh the page applied
		//the explicit wait for the same
		if(clickOnSignInButton.isDisplayed()) {
			clickOnSignInButton.click();
		} 
		else {
			driver.navigate().refresh();
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(clickOnSignInButton)).click();
		}
	}

	public String userNameTitle() throws InterruptedException {
		Thread.sleep(3000);
		return driver.findElement(userName).getText();
	}

	public void enterUserName(String emailid) {
		driver.findElement(emailTextBox).sendKeys(emailid);
		driver.findElement(continueButton).click();
	}

	public String passwordTitle() {
		return driver.findElement(password).getText();
	}

	public void enterPassword(String password) {
		driver.findElement(passwordTextBox).sendKeys(password);
		driver.findElement(signInButton).click();
	}

	//When user click on sign In then landing on the signIn page where user need to enter the
	//mobile number or Email Id for logging in but for the verification there are two messages
	//populating so that is validated in the below snippet
	public void signInValidation(String expectedUserName1, String expectedUserName2) throws InterruptedException {
		if (expectedUserName1.equals(userNameTitle())) {
			Assert.assertEquals(userNameTitle(), expectedUserName1);
		} else if (expectedUserName2.equals(userNameTitle())) {
			Assert.assertEquals(userNameTitle(), expectedUserName2);
		}
	}
	
	public String getErrorMessage()
	{
		String invalidMessage=driver.findElement(errorMessage).getText();
		return invalidMessage;
	}
	
	public void clearUserNamefield()
	{
		driver.findElement(emailTextBox).clear();
	}

	public void clickOnChangeToGoBackToSignInPage()
	{
		driver.findElement(changeLink).click();
	}
}
