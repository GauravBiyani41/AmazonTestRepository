package ExtentReportUtility;

import java.io.IOException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportBaseClass {

	public static ExtentTest test;
	public static ExtentSparkReporter htmlReport;
	public static ExtentReports reports;

	@BeforeSuite

	public void setUp() {
		
		htmlReport = new ExtentSparkReporter("ExtentReportNew.html");
		reports = new ExtentReports();
		reports.attachReporter(htmlReport);
		
		// add environment details
		
		reports.setSystemInfo("OS", "Windows10");
		reports.setSystemInfo("user", "Gaurav Biyani");
		reports.setSystemInfo("Browser", "Chrome");

		// Configuration to change look and feel of the Report
		htmlReport.config().setReportName("Test Report");
		htmlReport.config().setTheme(Theme.STANDARD);

	}

	@AfterMethod

	public void getResult(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAIL ", ExtentColor.RED));

		}

		else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASS ", ExtentColor.GREEN));
		}

		else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIP ", ExtentColor.YELLOW));
		}

	}

	@AfterSuite

	public void tearDown() {
		reports.flush();
	}
}
