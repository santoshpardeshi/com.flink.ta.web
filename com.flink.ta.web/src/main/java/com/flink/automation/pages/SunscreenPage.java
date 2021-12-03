package com.flink.automation.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.flink.automation.listener.ApplicationListener;
import com.flink.automation.utils.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class SunscreenPage extends BasePage {

    /** WebDriver. */
    private WebDriver driver;

    /** ExtentTest. */
    private ExtentTest extentTest;

    /** Aloe Sunscreen SPF-30 product list . */
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'text-center')]//p[contains(text(),'SPF-30') or contains(text(),'spf-30')]//following-sibling::p")
    private List<WebElement> listSPF30ProductPrice;

    /** Aloe Sunscreen SPF-30 product add button list . */
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'text-center')]//p[contains(text(),'SPF-30') or contains(text(),'spf-30')]//following-sibling::button")
    private List<WebElement> listSPF30ProductAddButton;

    /** Almond Sunscreen SPF-50 product list . */
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'text-center')]//p[contains(text(),'SPF-50') or contains(text(),'spf-50')]//following-sibling::p")
    private List<WebElement> listSPF50ProductPrice;

    /** Almond Sunscreen SPF-50 product add button list . */
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'text-center')]//p[contains(text(),'SPF-50') or contains(text(),'spf-50')]//following-sibling::button")
    private List<WebElement> listSPF50ProductAddButton;


    /**
     * SunscreenPage parameterized constructor.
     *
     * @param driver Object WebDriver
     */
    public SunscreenPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        extentTest = ApplicationListener.getExtentTest();
        extentTest.log(Status.INFO, "Navigate to the Sunscreen Page");
    }

    /**
     * Get SPF-30 product price list
     * @return List<String>
     */
    public List<String> getSPF30ProductPriceList() {
        return listSPF30ProductPrice.stream().map(spf30 -> spf30.getText()).collect(Collectors.toList());
    }

    /**
     * Add SPF-30 product to cart
     */
    public void selectSPF30Product() {
        extentTest.log(Status.INFO, "Add SPF-30 Product to cart");
        addProductToCart(listSPF30ProductAddButton, CommonUtils.getLeastExpensiveProductIndex(getSPF30ProductPriceList()));
    }

    /**
     * Get SPF-50 product price list
     * @return List<String>
     */
    public List<String> getSPF50ProductPriceList() {
        return listSPF50ProductPrice.stream().map(spf50 -> spf50.getText()).collect(Collectors.toList());
    }

    /**
     * Add SPF-50 product to cart
     */
    public void selectSPF50Product() {
        extentTest.log(Status.INFO, "Add SPF-50 Product to cart");
        addProductToCart(listSPF50ProductAddButton, CommonUtils.getLeastExpensiveProductIndex(getSPF50ProductPriceList()));
    }
}
