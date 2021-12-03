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

import java.util.List;

abstract public class BasePage {

    /** WebDriver. */
    private WebDriver driver;

    /** ExtentTest. */
    private ExtentTest extentTest;

    /** Tooltip icon. */
    @FindBy(how = How.CSS, using = "[class='octicon octicon-info']")
    private WebElement iconTooltip;

    @FindBy(how = How.CSS, using = "[class='popover-body']")
    private WebElement popupTooltipMessage;

    @FindBy(how = How.CSS, using = "[class='thin-text nav-link']")
    private WebElement cartButton;

    /**
     * BasePage parameterized constructor.
     *
     * @param driver Object WebDriver
     */
    public BasePage(final WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        extentTest = ApplicationListener.getExtentTest();
    }

    /**
     * click on page tooltip icon
     */
    public void clickOnTooltipIcon() {
        extentTest.log(Status.INFO, "Click on Tooltip icon");
        iconTooltip.click();
    }

    /**
     * Get tooltip text
     * @return String
     */
    public String getTooltipMessage() {
        extentTest.log(Status.INFO, "Get tooltip message");
        SeleniumUtils.isVisible(popupTooltipMessage, driver);
        return popupTooltipMessage.getText();
    }

    /**
     * Add product to cart
     * @param productButtonList : product button list
     * @param index : button index to select
     */
    public void addProductToCart(List<WebElement> productButtonList, int index) {
        SeleniumUtils.isClickable(productButtonList.get(index), driver);
        productButtonList.get(index).click();
    }

    /**
     * click om cart button
     * @return CartPage page
     */
    public CartPage clickOnCartButton() {
        extentTest.log(Status.INFO, "Click on cart button");
        SeleniumUtils.isVisible(cartButton, driver);
        cartButton.click();
        return new CartPage(driver);
    }

}
