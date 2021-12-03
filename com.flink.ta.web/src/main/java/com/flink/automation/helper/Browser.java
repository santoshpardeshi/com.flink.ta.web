package com.flink.automation.helper;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.flink.automation.constant.FrameworkConstants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser {
	
	/** The webDriver driver. */
	private WebDriver driver;
	
	
	/**
	 * Initiate browser and open the application URL in Web Browser
	 *
	 * @param browserType
	 *            the browser type on which the test will be run
	 *
	 */

	public void initiateBrowser(final String browserType) {

		if (browserType.toUpperCase() != null) {
			switch (browserType.toUpperCase()) {
			case FrameworkConstants.BROWSER_FIREFOX:
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;

			case FrameworkConstants.BROWSER_EDGE:
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;

			case FrameworkConstants.BROWSER_CHROME:
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			case FrameworkConstants.BROWSER_SAFARI:
				WebDriverManager.safaridriver().setup();
				driver = new SafariDriver();
				break;
			default:
				break;
			}

			if (null != driver) {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.manage().window().maximize();
			}
		}
	}
	

	/**
	 *	Get WebDriver working instance 
	 * @return WebDriver
	 */
	public WebDriver getDriver() {
		return this.driver;
	}
	
	public void initiateApplication(final String applicationURL) {
		if (null != getDriver()) {
			getDriver().get(applicationURL);
		}
	}

	
	/**
	 * quit browser instance.
	 * @throws Exception
	 */
	public void tearDown() throws Exception {
		getDriver().quit();
	}
}
