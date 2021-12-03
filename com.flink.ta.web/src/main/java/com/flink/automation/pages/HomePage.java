package com.flink.automation.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.flink.automation.listener.ApplicationListener;
import com.flink.automation.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{

    /** WebDriver. */
    private WebDriver driver;

    /** ExtentTest. */
    private ExtentTest extentTest;

    /** temperature Text. */
    @FindBy(how = How.CSS, using = "#temperature")
    private WebElement temperatureText;

    /** moisturizer Button*/
    @FindBy(how = How.CSS, using = "a[href*='moisturizer'] button")
    private WebElement moisturizerButton;

    /** sunscreen Button*/
    @FindBy(how = How.CSS, using = "a[href*='sunscreen'] button")
    private WebElement sunscreenButton;

    /**
     * HomePage parameterized constructor.
     *
     * @param driver Object WebDriver
     */
    public HomePage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        extentTest = ApplicationListener.getExtentTest();
        extentTest.log(Status.INFO, "Navigate to the Home Page");
    }

    /**
     * get temperature
     * @return Integer
     */
    public int getTemperature() {
        extentTest.log(Status.INFO, "Get Temperature index");
        return Integer.parseInt(temperatureText.getText().trim().replaceAll("[^\\d]", "").replace("\n", "").
                replace("\r", ""));
    }

    /**
     * Click on Moisturizer Button
     * @return MoisturizerPage
     */
    public MoisturizerPage clickOnMoisturizerButton() {
        extentTest.log(Status.INFO, "Click on moisturizer button");
        SeleniumUtils.isClickable(moisturizerButton, driver);
        moisturizerButton.click();
        return new MoisturizerPage(driver);
    }

    /**
     *Click on sunscreen Button
     * @return SunscreenPage
     */
    public SunscreenPage clickOnSunscreenButton() {
        extentTest.log(Status.INFO, "Click on sunscreen button");
        SeleniumUtils.isClickable(sunscreenButton, driver);
        sunscreenButton.click();
        return new SunscreenPage(driver);
    }
}
