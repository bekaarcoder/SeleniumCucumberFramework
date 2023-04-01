package org.autozone.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumUtils {
    private static WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public static void clickElement(WebDriver driver, By locator) {
        WebElement element = waitForElementVisibility(driver, locator);
        getWait(driver).until(d -> ExpectedConditions.elementToBeClickable(element).apply(d)).click();
    }

    public static boolean isElementDisplayed(WebDriver driver, By locator) {
        WebElement element = waitForElementVisibility(driver, locator);
        return element.isDisplayed();
    }

    public static void enterKeys(WebDriver driver, By locator, String keys) {
        WebElement element = waitForElementVisibility(driver, locator);
        element.sendKeys(keys);
    }

    public static WebElement waitForElementVisibility(WebDriver driver, By locator) {
        return getWait(driver).until(d -> ExpectedConditions.visibilityOfElementLocated(locator).apply(d));
    }

    public static void waitForElementInvisiblity(WebDriver driver, By locator) {
        getWait(driver).until(d -> ExpectedConditions.invisibilityOfElementLocated(locator).apply(d));
    }
}
