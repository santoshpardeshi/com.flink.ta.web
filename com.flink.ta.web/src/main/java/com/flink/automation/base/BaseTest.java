package com.flink.automation.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.flink.automation.helper.Browser;
import com.flink.automation.helper.CustomizedException;
import com.flink.automation.utils.ConfigurationReader;

public class BaseTest {

	/** browser type. */
	protected String browserType;

	/** Application URL . */
	protected String applicationURL;

	/** Object Browser. */
	protected Browser browser;
	
	/** Object ConfigurationReader. */
	private static ConfigurationReader configurationReader = ConfigurationReader.getInstance();

	/** Object Properties. */
	private Properties properties = null;

	@BeforeMethod
	public void setupMethod() {
		try {
			browser = new Browser();
			browser.initiateBrowser(configurationReader.getProperty("browser.type"));
			browser.initiateApplication(configurationReader.getProperty("application.url"));

		} catch (final Exception exception) {
			throw new CustomizedException(exception.getMessage());
		}
	}

	@AfterMethod
	public void quitMethod() {
		try {
			getBrowser().tearDown();
		} catch (final Exception exception) {
			throw new CustomizedException("Error: ", exception);
		}
	}

	/**
	 * This method is to get browser type.
	 * 
	 * @return browser type
	 */
	public String getBrowserType() {
		return browserType;
	}

	/**
	 * This method is to get application URL.
	 * 
	 * @return application URL
	 */
	public String getApplicationURL() {
		return applicationURL;
	}

	/**
	 * This method is to get object for Browser.
	 * 
	 * @return object Browser
	 */
	public Browser getBrowser() {
		return this.browser;
	}

	/**
	 * This method is used to get instance of web driver.
	 * 
	 * @return Object WebDriver
	 */
	public WebDriver getDriver() {
		return getBrowser().getDriver();
	}

	/**
	 * This method is used to get property value by using key.
	 * 
	 * @param key property key
	 * @return property value
	 */
	public String getProperty(final String key) {
		String value = null;
		if (properties.containsKey(key)) {
			value = (String) properties.get(key);
		}
		return value;
	}
}
