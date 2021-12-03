package com.flink.automation.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.flink.automation.listener.ApplicationListener;
import com.flink.automation.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage{

    /** WebDriver. */
    private WebDriver driver;

    /** ExtentTest. */
    private ExtentTest extentTest;

    /** item Table. */
    @FindBy(how = How.CSS, using = "[class*='table-striped']")
    private WebElement itemTable;

    /**
     * CartPage parameterized constructor.
     *
     * @param driver Object WebDriver
     */
    public CartPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        extentTest = ApplicationListener.getExtentTest();
        extentTest.log(Status.INFO, "Navigate to the Cart Page");
    }

    public int getTotalRowInTable() {
        extentTest.log(Status.INFO, "Get Total product added to cart.");
        SeleniumUtils.isVisible(itemTable, driver);
        return itemTable.findElements(By.cssSelector("tr")).size() - 1;
    }
}
