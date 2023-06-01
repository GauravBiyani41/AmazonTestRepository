package TestCases;

import org.openqa.selenium.WebDriver;
import ExtentReportUtility.ExtentReportBaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import JavaGenericUtility.Utility;
import PageObjects.SignInPage;
import PageObjects.HomePage;
import PageObjects.UserAddressPage;

public class TC02_AddingUserNewAddress extends ExtentReportBaseClass {

	WebDriver driver;

	@Test
	public void addingTheNewAddress() throws InterruptedException {

		Utility utilityObj = new Utility(driver);
		WebDriver driver = utilityObj.setUp("chrome");

		SignInPage signInPageObj = new SignInPage(driver);
		HomePage homePageObj = new HomePage(driver);
		UserAddressPage userAddressObj = new UserAddressPage(driver);

		String emailId = "biyanigaurav140@gmail.com";
		String password = "Gaurav@0210";
		String expectedUserName1 = "Email or mobile phone number";
		String expectedUserName2 = "Enter mobile phone number or email";
		String ValidUserNameOnHomePage = "Hello, Gaurav";
		String addedAddressSuccessfully = "Address saved";
		String fullName = "Gaurav Sanjay Biyani";
		String phoneNumber = "8698163640";
		String pinCode = "444403";
		String addressLine1 = "401,Near Mangrulpir BusStand";
		String addressLine2 = "Mangrulpir";
		String addressLine3 = "Washim";
		String deletedMessageText = "Address deleted";
		String testCaseName = "TC02_AddingUserNewAddress.java";

		test = reports.createTest(testCaseName);

		signInPageObj.clickOnSignIn();
		signInPageObj.enterUserName(emailId);
		signInPageObj.enterPassword(password);

		Assert.assertEquals(ValidUserNameOnHomePage, homePageObj.userNameOnHomePage());

		homePageObj.clickOnAll();
		homePageObj.clickOnYourAccount();
		homePageObj.clickOnyourAddresses();
		userAddressObj.enterUserAddress(fullName, phoneNumber, pinCode, addressLine1, addressLine2, addressLine3);

		Assert.assertEquals(userAddressObj.addedAddressSaved(), addedAddressSuccessfully);

		userAddressObj.removeAddress();
		userAddressObj.addressDeletedMessage();

		Assert.assertEquals(deletedMessageText, userAddressObj.addressDeletedMessage());

		homePageObj.clickOnAll();
		homePageObj.clickOnSignOut();

		// for validating that user landed on signIn Page
		signInPageObj.signInValidation(expectedUserName1, expectedUserName2);
	}

}
