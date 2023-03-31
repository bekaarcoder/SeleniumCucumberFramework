package org.autozone.pages;

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

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public boolean isCartPage() {
        return this.wait.until(driver -> ExpectedConditions.visibilityOfElementLocated(checkoutBtnLocator).apply(driver)).isDisplayed();
    }

    public void navigateToCheckout() {
        this.wait.until(driver -> ExpectedConditions.elementToBeClickable(checkoutBtnLocator).apply(driver)).click();
    }
}
