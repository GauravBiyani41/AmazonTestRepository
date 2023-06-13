package TestCases;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



import ExtentReportUtility.ExtentReportBaseClass;
import JavaGenericUtility.Utility;
import PageObjects.HomePage;
import PageObjects.ResultOfTheSearch;

public class TC08_SortProductBasedOnRating extends ExtentReportBaseClass{

	WebDriver driver = null;
	
	@Test

	public void SortProductBasedOnRating() throws InterruptedException {

		
		int ratingsOfTheProduct = Integer.parseInt(System.getProperty("rating"));
		
		Utility utilityObj = new Utility(driver);
		WebDriver driver = utilityObj.setUp("chrome");

		String enterDataIntoSearchField = "Samsung Mobile";
		String productsRatings;
		String testCaseName = "TC08_SortProductBasedOnRating";
		
		HomePage homePageObj = new HomePage(driver);

		ResultOfTheSearch resultOfTheSearchObj = new ResultOfTheSearch(driver);
		
		test = reports.createTest(testCaseName);
		
		homePageObj.enterDataIntoTheSearchField(enterDataIntoSearchField);
		
		resultOfTheSearchObj.getTheRatingAndClickOnTheRating(ratingsOfTheProduct);
		resultOfTheSearchObj.fetchedTheRatingsOfTheProducts();
		
		// for loop for fetching and validating individual ratings of the products is
		// grater than equal to rating provided by the user
		for (int i = 0; i < resultOfTheSearchObj.resultsOfTheRatingsOfTheProducts.size(); i++) 
		{
			resultOfTheSearchObj.fetchedTheRatingsOfTheProducts();
			productsRatings = resultOfTheSearchObj.resultsOfTheRatingsOfTheProducts.get(i).getAttribute("innerText");
		    productsRatings = productsRatings.substring(0, 3);
			double ratings = Double.parseDouble(productsRatings);
			Assert.assertTrue(ratings >= ratingsOfTheProduct);
		}
	}
}
