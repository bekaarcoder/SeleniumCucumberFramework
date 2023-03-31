package org.autozone.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By productHeaderLocator = By.xpath("//h1[@data-testid='at_title_first_text']");
    private final By cartButtonLocator = By.xpath("//button[@data-testid='cart-action-shelf-0']");
    private final By viewCartAndCheckoutBtnLocator = By.xpath("//button[@data-testid='view-cart-and-checkout-button']");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public boolean isProductPage() {
        return this.wait.until(driver -> ExpectedConditions.visibilityOfElementLocated(productHeaderLocator).apply(driver)).isDisplayed();
    }

    public void addToCart() {
        this.wait.until(driver -> ExpectedConditions.elementToBeClickable(cartButtonLocator).apply(driver)).click();
    }

    public void navigateToCart() {
        this.wait.until(driver -> ExpectedConditions.elementToBeClickable(viewCartAndCheckoutBtnLocator).apply(driver)).click();
    }

}
