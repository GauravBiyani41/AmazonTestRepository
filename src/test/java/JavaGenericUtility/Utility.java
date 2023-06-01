package JavaGenericUtility;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class Utility {

	public WebDriver driver = null;

	public Utility(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriver setUp(String browserName) {

		if (browserName.equalsIgnoreCase("Chrome")) {

			WebDriverManager.chromedriver().setup();
			ChromeOptions opt = new ChromeOptions();
			// as In Selenium there is bug for chrome browser version 111 so due to
			// added the ChromeOptions class
			opt.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(opt);

		} else if (browserName.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			EdgeOptions opt = new EdgeOptions();
			// as In Selenium there is bug for edge browser version 111 so due to
			// added the EdgeOptions class
			opt.addArguments("--remote-allow-origins=*");
			driver = new EdgeDriver(opt);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://www.amazon.in/");
		return driver;

	}


	
}
