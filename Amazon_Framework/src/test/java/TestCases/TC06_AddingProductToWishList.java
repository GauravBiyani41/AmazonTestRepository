package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import ExtentReportUtility.ExtentReportBaseClass;
import PageObjects.SignInPage;
import PageObjects.HomePage;
import PageObjects.ProductDescriptionPage;
import PageObjects.ProductsPage;
import PageObjects.WishListPage;
import org.openqa.selenium.WebDriver;
import JavaGenericUtility.Utility;

public class TC06_AddingProductToWishList extends ExtentReportBaseClass {
	WebDriver driver = null;

	@Test

	public void AddingProductIntoTheCart() throws InterruptedException {
		Utility utilityObj = new Utility(driver);
		WebDriver driver = utilityObj.setUp("chrome");

		String emailId = "biyanigaurav140@gmail.com";
		String password = "Gaurav@0210";
		String enterDataIntoSearchField = "Samsung Mobile";
		String wishListPageTitle = "Your Lists";
		String productDeletedMessage = "Deleted";
		String expectedUserName1 = "Email or mobile phone number";
		String expectedUserName2 = "Enter mobile phone number or email";
		String productName = "Samsung";
		String testCaseName = "TC06_AddingProductToWishList";
		
		SignInPage signInPageObj = new SignInPage(driver);
		HomePage homePageObj = new HomePage(driver);
		ProductsPage productsPageObj = new ProductsPage(driver);
		ProductDescriptionPage proDescriptionObj = new ProductDescriptionPage(driver);
		WishListPage wishListPageObj = new WishListPage(driver);

		test = reports.createTest(testCaseName);
		
		signInPageObj.clickOnSignIn();
		signInPageObj.enterUserName(emailId);
		signInPageObj.enterPassword(password);

		homePageObj.enterDataIntoTheSearchField(enterDataIntoSearchField);
	
		productsPageObj.clickOnTheProduct();
		productsPageObj.switchToProductDescriptionWindow();
		proDescriptionObj.clickOnWishList();
		proDescriptionObj.clickOnViewYourList();
		
		Assert.assertEquals(wishListPageTitle, wishListPageObj.getWishListTitle());	
		Assert.assertEquals(productName, wishListPageObj.getWishListProductName());

		wishListPageObj.clickOnDeleteTheWishListProduct();
		wishListPageObj.getDeletedWishListMessage();

		Assert.assertEquals(productDeletedMessage, wishListPageObj.getDeletedWishListMessage());

		homePageObj.clickOnAll();
		homePageObj.clickOnSignOut();
		
		// for validating that user landed on signIn Page	
		signInPageObj.signInValidation(expectedUserName1, expectedUserName2);
	}
}
