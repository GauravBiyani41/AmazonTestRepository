package TestCases;

import org.testng.annotations.Test;

import ExtentReportUtility.ExtentReportBaseClass;
import PageObjects.SignInPage;
import PageObjects.HomePage;
import org.openqa.selenium.WebDriver;
import JavaGenericUtility.Utility;

public class TC07_ChangingUserAddressOnHomePage extends ExtentReportBaseClass {

	WebDriver driver = null;

	@Test

	public void changingUserAddressOnHomePage() throws InterruptedException {
		Utility utilityObj = new Utility(driver);
		WebDriver driver = utilityObj.setUp("chrome");

		String emailId = "biyanigaurav140@gmail.com";
		String password = "Gaurav@0210";
		String defaultAddress1 = "444403";
		String defaultAddress2 = "421204";
		String checkingAddress1 = "Mangrulpir 444403";
		String checkAddress2 = "Kalyan 421204‌";
		String expectedUserName1 = "Email or mobile phone number";
		String expectedUserName2 = "Enter mobile phone number or email";
		String testCaseName = "TC07_ChangingUserAddressOnHomePage";
		
		HomePage homePageObj = new HomePage(driver);
		SignInPage signInPageObj = new SignInPage(driver);

		test = reports.createTest(testCaseName);
		
		signInPageObj.clickOnSignIn();
		signInPageObj.enterUserName(emailId);
		signInPageObj.enterPassword(password);
		homePageObj.getDefaultAddressText();
		homePageObj.changeUserAddressOnHomePage();

		String fetchedString = homePageObj.getDefaultAddressText();

		//changing the address on the home page
		homePageObj.changeAddress(fetchedString, defaultAddress1, defaultAddress2, 
				checkAddress2, checkingAddress1);
		
		homePageObj.clickOnAll();
		homePageObj.clickOnSignOut();
		// for validating that user landed on signIn Page
		signInPageObj.signInValidation(expectedUserName1, expectedUserName2);

	}
}
