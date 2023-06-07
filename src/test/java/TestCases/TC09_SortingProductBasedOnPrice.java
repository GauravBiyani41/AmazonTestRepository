package TestCases;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import ExtentReportUtility.ExtentReportBaseClass;
import JavaGenericUtility.Utility;
import PageObjects.HomePage;
import PageObjects.ResultOfTheSearch;


public class TC09_SortingProductBasedOnPrice extends ExtentReportBaseClass {

	WebDriver driver = null;

	@Test
	public void SortingProductBasedOnPrice() throws InterruptedException {

//		System.out.println("Enter the input price for the test case 9 for sorting the product based on price");
//		System.out.println("Enter the lowest price ");
//		@SuppressWarnings("resource")
//		Scanner sc = new Scanner(System.in);
		String lowPrice = "10000";
//		System.out.println("Enter the highest price ");
		String maxPrice = "20000";
		Utility utilityObj = new Utility(driver);
		WebDriver driver = utilityObj.setUp("chrome");
		
		String enterDataIntoSearchField = "Samsung Mobile";

		String testCaseName = "TC09_SortingProductBasedOnPrice";
		int lowPriceInteger = Integer.parseInt(lowPrice);
		int highPriceInteger = Integer.parseInt(maxPrice);

		HomePage homePageObj = new HomePage(driver);

		ResultOfTheSearch resultOfTheSearchObj = new ResultOfTheSearch(driver);

		test = reports.createTest(testCaseName);
		
		homePageObj.enterDataIntoTheSearchField(enterDataIntoSearchField);
		resultOfTheSearchObj.enterPrice(lowPrice, maxPrice);
		resultOfTheSearchObj.getPricesOfTheProducts();
		// for loop for fetching and validating individual price of the products is
		// in between the specified range or not
		for (int i = 0; i < resultOfTheSearchObj.prices.size(); i++) {
			String price = resultOfTheSearchObj.prices.get(i).getText();
			String priceWithoutSpecialChar = price.replace(",", "");
			int getPrice = (int) Integer.valueOf(priceWithoutSpecialChar);
			Assert.assertTrue(getPrice > lowPriceInteger && getPrice < highPriceInteger);
		}

	}

}