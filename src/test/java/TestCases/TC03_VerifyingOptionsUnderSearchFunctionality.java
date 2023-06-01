package TestCases;


import java.util.ArrayList;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import ExtentReportUtility.ExtentReportBaseClass;
import JavaGenericUtility.Utility;
import PageObjects.HomePage;

public class TC03_VerifyingOptionsUnderSearchFunctionality extends ExtentReportBaseClass {

	WebDriver driver = null;

	@Test
	public void verifyingOptionsUnderSearch() throws InterruptedException {

		Utility utilityObj = new Utility(driver);
		WebDriver driver = utilityObj.setUp("chrome");

		HomePage homePageObj = new HomePage(driver);

		String testCaseName = "TC03_VerifyingOptionsUnderSearchFunctionality";
		
		test = reports.createTest(testCaseName);
		
		homePageObj.differentOptionsunderSearch();
	
		List<Object> optionsList = new ArrayList<Object>();

		optionsList.add("Amazon miniTV");
		optionsList.add("Sell");
		optionsList.add("Best Sellers");
		optionsList.add("Mobiles");
		optionsList.add("Today's Deals");
		optionsList.add("Customer Service");
		optionsList.add("New Releases");
		optionsList.add("Electronics");

		for (int i=0; i<8; i++) {

			Assert.assertTrue(homePageObj.differentOptions.get(i).getText().equals(optionsList.get(i)));
		}

	}
}
