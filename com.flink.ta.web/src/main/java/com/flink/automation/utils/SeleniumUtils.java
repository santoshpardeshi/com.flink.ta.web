package com.flink.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {

    /**
     * Checks if is visible.
     *
     * @param element the element
     * @param driver the driver
     * @return true, if is visible
     */
    public static boolean isVisible(WebElement element, WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if is clickable.
     *
     * @param element the element
     * @param driver the driver
     * @return true, if is clickable
     */
    public static boolean isClickable(WebElement element, WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
