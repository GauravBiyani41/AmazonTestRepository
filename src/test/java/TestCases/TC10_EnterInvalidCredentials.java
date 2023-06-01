package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import ExtentReportUtility.ExtentReportBaseClass;
import JavaGenericUtility.Utility;
import PageObjects.HomePage;
import PageObjects.SignInPage;

public class TC10_EnterInvalidCredentials extends ExtentReportBaseClass{

	WebDriver driver = null;

	@Test

	public void EnterInvalidCredentials() throws InterruptedException {

		Utility utilityObj = new Utility(driver);
		WebDriver driver = utilityObj.setUp("chrome");

		String invalidEmailId = "biyanigaurav111@gmail.com";
		String errorMessageforInvalidMailId = "We cannot find an account with that email address";
		String invalidMobileNumber = "1234567891";
		String errorMessageforInvalidMobileNumber = "We cannot find an account with that mobile number";
		String validMobileNumber = "8698163640";
		String validEmailId = "biyanigaurav140@gmail.com";
		String enterPassword = "Gaurav@10";
		String invalidPasswordMessage = "Your password is not correct";
		String expectedTitle = "Amazon.in";
		String expectedUserName1 = "Email or mobile phone number";
		String expectedUserName2 = "Enter mobile phone number or email";
		String expectedPasswordTitle = "Password";
		String testCaseName = "TC10_EnterInvalidCredentials";
		
		HomePage homePageObj = new HomePage(driver);
		SignInPage signInPageObj = new SignInPage(driver);

		
		test = reports.createTest(testCaseName);
		
		homePageObj.amazonHomePageTitle();
		Assert.assertEquals(homePageObj.amazonHomePageTitle(), expectedTitle, "Title Matched");
		signInPageObj.clickOnSignIn();
		signInPageObj.userNameTitle();
		
		// for validating that user landed on signIn Page
		signInPageObj.signInValidation(expectedUserName1, expectedUserName2);
		signInPageObj.enterUserName(invalidEmailId);
		Assert.assertEquals(errorMessageforInvalidMailId, signInPageObj.getErrorMessage());

		signInPageObj.clearUserNamefield();
		signInPageObj.enterUserName(invalidMobileNumber);
		Assert.assertEquals(errorMessageforInvalidMobileNumber, signInPageObj.getErrorMessage());

		signInPageObj.clearUserNamefield();
		signInPageObj.enterUserName(validEmailId);
		Assert.assertEquals(expectedPasswordTitle, signInPageObj.passwordTitle());

		signInPageObj.clickOnChangeToGoBackToSignInPage();
		signInPageObj.enterUserName(validMobileNumber);
		Assert.assertEquals(expectedPasswordTitle, signInPageObj.passwordTitle());

		signInPageObj.enterPassword(enterPassword);
		Assert.assertEquals(invalidPasswordMessage, signInPageObj.getErrorMessage());

	}

}
