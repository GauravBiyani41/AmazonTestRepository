package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import ExtentReportUtility.ExtentReportBaseClass;
import JavaGenericUtility.Utility;
import PageObjects.HomePage;
import PageObjects.ProductsPage;

public class TC04_SearchFunctionality extends ExtentReportBaseClass{

	WebDriver driver;

	@Test

	public void verifyingSearchFunctionality() throws InterruptedException {
		Utility utilityObj = new Utility(driver);
		WebDriver driver = utilityObj.setUp("chrome");

		String enterDataIntoSearchField = "Samsung";

		String testCaseName = "TC04_SearchFunctionality";
		
		HomePage homePageObj = new HomePage(driver);
		ProductsPage productsPageObj = new ProductsPage(driver);

		homePageObj.enterDataIntoTheSearchField(enterDataIntoSearchField);

		test = reports.createTest(testCaseName);
		// validating the entered item is searched or not ?
		Assert.assertEquals(productsPageObj.verifyResultsofTheSearch(), enterDataIntoSearchField);

	}
}
