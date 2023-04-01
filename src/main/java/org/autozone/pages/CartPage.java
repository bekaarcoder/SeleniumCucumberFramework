package org.autozone.pages;

import org.autozone.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By checkoutBtnLocator = By.xpath("//div[@data-testid='cart-checkout-button']//button");
    private final By loaderLocator = By.xpath("//*[@data-testid='progressModal']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public boolean isCartPage() {
        return this.wait.until(driver -> ExpectedConditions.visibilityOfElementLocated(checkoutBtnLocator).apply(driver)).isDisplayed();
    }

    public void navigateToCheckout() {
        SeleniumUtils.waitForElementInvisibility(this.driver, loaderLocator);
        SeleniumUtils.clickElement(this.driver, checkoutBtnLocator);
    }
}
