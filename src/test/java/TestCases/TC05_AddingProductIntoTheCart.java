package TestCases;

import org.openqa.selenium.WebDriver;

import org.testng.Assert;

import org.testng.annotations.Test;

import ExtentReportUtility.ExtentReportBaseClass;
import JavaGenericUtility.Utility;
import PageObjects.SignInPage;
import PageObjects.HomePage;
import PageObjects.ProductsPage;

public class TC05_AddingProductIntoTheCart extends ExtentReportBaseClass {

	WebDriver driver = null;

	@Test

	public void AddingProductIntoTheCart() throws InterruptedException {

		Utility utilityObj = new Utility(driver);
		WebDriver driver = utilityObj.setUp("chrome");

		String emailId = "biyanigaurav140@gmail.com";
		String password = "Gaurav@0210";
		String enterDataIntoSearchField = "Samsung Mobile";
		String productDetails = "See more product details";
		String cartTitle = "Shopping Cart";
		String productDeletedMessage = "Your Amazon Cart is empty.";
		String expectedUserName1 = "Email or mobile phone number";
		String expectedUserName2 = "Enter mobile phone number or email";
		String productName = "Samsung";
		String addressPageTitle = " Select a payment method";
		String testCaseName = "TC05_AddingProductIntoTheCart";
		
		SignInPage signInPageObj = new SignInPage(driver);
		HomePage homePageObj = new HomePage(driver);
		ProductsPage productsPageObj = new ProductsPage(driver);

		test = reports.createTest(testCaseName);
		signInPageObj.clickOnSignIn();
		signInPageObj.enterUserName(emailId);
		signInPageObj.enterPassword(password);

		homePageObj.enterDataIntoTheSearchField(enterDataIntoSearchField);
		// click on the first product
		productsPageObj.clickOnTheProduct();
		productsPageObj.switchToProductDescriptionWindow();

		// validating the after clicking on the product it wents to
		// the new tab and landed to the description page of the product
		Assert.assertEquals(productDetails, productsPageObj.verifyProductDescription());
		productsPageObj.clickOnAddToCart();

		Assert.assertEquals(cartTitle, productsPageObj.verifyCartTitle());
		
		// product has been added or not into the cart

		Assert.assertEquals(productName, productsPageObj.verifyProductInCart());
		productsPageObj.clickOnBuyProduct();
		
		Assert.assertEquals(addressPageTitle, productsPageObj.getAddressPageTitle());
		
		productsPageObj.backAndDeleteProductFromCart();

		Assert.assertEquals(productDeletedMessage, productsPageObj.deleteProductMessage());

		homePageObj.clickOnAll();
		homePageObj.clickOnSignOut();
		
		// for validating that user landed on signIn Page
		signInPageObj.signInValidation(expectedUserName1, expectedUserName2);
	}
}
