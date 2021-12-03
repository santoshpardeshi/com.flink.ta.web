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

public class MoisturizerPage extends BasePage{

    /** WebDriver. */
    private WebDriver driver;

    /** ExtentTest. */
    private ExtentTest extentTest;

    /** Aloe Moisturizer product list . */
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'text-center')]//p[contains(text(),'Aloe') or contains(text(),'aloe')]//following-sibling::p")
    private List<WebElement> listAloeProductPrice;

    /** Aloe Moisturizer product add button list . */
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'text-center')]//p[contains(text(),'Aloe') or contains(text(),'aloe')]//following-sibling::button")
    private List<WebElement> listAloeProductAddButton;

    /** Almond Moisturizer product list . */
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'text-center')]//p[contains(text(),'Almond') or contains(text(),'almond')]/following-sibling::p")
    private List<WebElement> listAlmondProductPrice;

    /** Almond Moisturizer product add button list . */
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'text-center')]//p[contains(text(),'Almond') or contains(text(),'almond')]/following-sibling::button")
    private List<WebElement> listAlmondProductAddButton;

    /**
     * MoisturizerPage parameterized constructor.
     *
     * @param driver Object WebDriver
     */
    public MoisturizerPage(final WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
        extentTest = ApplicationListener.getExtentTest();
        extentTest.log(Status.INFO, "Navigate to the Moisturizer Page");
    }

    /**
     * Get aloe product price list
     * @return List<String>
     */
    public List<String> getAloeProductPriceList() {
        return listAloeProductPrice.stream().map(aloePrice -> aloePrice.getText()).collect(Collectors.toList());
    }

    /**
     * Add Aloe product to cart
     */
    public void selectAloeProduct() {
        extentTest.log(Status.INFO, "Add Aloe Product to cart");
        addProductToCart(listAloeProductAddButton, CommonUtils.getLeastExpensiveProductIndex(getAloeProductPriceList()));
    }

    /**
     * Get almond product price list
     * @return List<String>
     */
    public List<String> getAlmondProductPriceList() {
        return listAlmondProductPrice.stream().map(almondPrice -> almondPrice.getText()).collect(Collectors.toList());
    }

    /**
     * Add Almond product to cart
     */
    public void selectAlmondProduct() {
        extentTest.log(Status.INFO, "Add Almond Product to cart");
        addProductToCart(listAlmondProductAddButton, CommonUtils.getLeastExpensiveProductIndex(getAlmondProductPriceList()));
    }

}
