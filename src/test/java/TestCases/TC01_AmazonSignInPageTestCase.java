package TestCases;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;

import org.testng.annotations.Test;


import PageObjects.SignInPage;
import PageObjects.HomePage;

import JavaGenericUtility.Utility;
import ExtentReportUtility.ExtentReportBaseClass;

public class TC01_AmazonSignInPageTestCase extends ExtentReportBaseClass {

	WebDriver driver = null;
	
	@Test
	public void signningToAmazon() throws InterruptedException {

		Utility utilityObj = new Utility(driver);
		WebDriver driver = utilityObj.setUp("chrome");

		SignInPage signInPageObj = new SignInPage(driver);
		HomePage homePageObj = new HomePage(driver);

		String expectedTitle = "Amazon.in";
		String expectedUserName1 = "Email or mobile phone number";
		String expectedUserName2 = "Enter mobile phone number or email";
		String expectedPasswordTitle = "Password";
		String validUserNameOnHomePage = "Hello, Gaurav";
		String password = "Gaurav@0210";
		String emailId = "biyanigaurav140@gmail.com";
		String testCaseName = "TC01_AmazonSignInPageTestCase";

		test = reports.createTest(testCaseName);
		
		Assert.assertEquals(homePageObj.amazonHomePageTitle(), expectedTitle);

		signInPageObj.clickOnSignIn();
		signInPageObj.userNameTitle();

		// for validating that user landed on signIn Page
		signInPageObj.signInValidation(expectedUserName1, expectedUserName2);

		signInPageObj.enterUserName(emailId);

		Assert.assertEquals(signInPageObj.passwordTitle(), expectedPasswordTitle);

		signInPageObj.enterPassword(password);

		Assert.assertEquals(validUserNameOnHomePage, homePageObj.userNameOnHomePage());

		homePageObj.clickOnAll();
		homePageObj.clickOnSignOut();
		// for validating that user landed on signIn Page
		signInPageObj.signInValidation(expectedUserName1, expectedUserName2);

	}
	

}
