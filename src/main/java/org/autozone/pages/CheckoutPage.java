package org.autozone.pages;

import com.github.javafaker.Faker;
import org.autozone.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Locale;

public class CheckoutPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Faker faker = new Faker(new Locale("en-US"));
    private SeleniumUtils seleniumUtils = new SeleniumUtils();

    // Locators
    private final By checkoutSectionLocator = By.xpath("//section[@data-testid='checkout-main-content']");
    private final By guestLinkLocator = By.id("continueAsGuestCartTrigger");
    private final By deliveryOptionLocator = By.xpath("//div[contains(@aria-label, 'step 2')]");
    private final By paymentRewardsLocator = By.xpath("//div[contains(@aria-label, 'step 3')]");
    private final By billingAddressLocator = By.xpath("//div[contains(@aria-label, 'step 4')]");
    private final By deliveryOptionContinueBtnLocator = By.xpath("//*[@data-testid='delivery-options-continue-btn']");
    private final By loaderLocator = By.xpath("//*[@data-testid='progressModal']");
    private final By cardNumberLocator = By.id("cardNumber");
    private final By expiryLocator = By.id("expiry");
    private final By securityCodeLocator = By.id("securityCode");
    private final By paymentCountinueBtnLocator = By.id("at_submit_button_address_form");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public boolean isCheckoutPage() {
        return this.wait.until(driver -> ExpectedConditions.visibilityOfElementLocated(checkoutSectionLocator).apply(driver)).isDisplayed();
    }

    public void clickOnGuestLink() {
        this.wait.until(driver -> ExpectedConditions.invisibilityOfElementLocated(loaderLocator).apply(driver));
        this.wait.until(driver -> ExpectedConditions.elementToBeClickable(guestLinkLocator).apply(driver)).click();
    }

    public boolean isDeliveryOptionEnabled() {
        WebElement deliveryOptionSection = driver.findElement(deliveryOptionLocator);
        String isDisabled =  deliveryOptionSection.getAttribute("aria-disabled");
        return !Boolean.parseBoolean(isDisabled);
    }

    public void clickOnContinueForDeliveryOption() {
        this.wait.until(driver -> ExpectedConditions.elementToBeClickable(deliveryOptionContinueBtnLocator).apply(driver)).click();
    }

    public boolean isPaymentInformationEnabled() {
        WebElement paymentInformationSection = driver.findElement(paymentRewardsLocator);
        String isDisabled =  paymentInformationSection.getAttribute("aria-disabled");
        return !Boolean.parseBoolean(isDisabled);
    }

    public void enterCardNumber(String cardNumber) {
        WebElement cardNumberField = driver.findElement(cardNumberLocator);
        cardNumberField.sendKeys(cardNumber);
    }

    public void enterExpiry(String expiry) {
        WebElement expiryField = driver.findElement(expiryLocator);
        expiryField.sendKeys(expiry);
    }

    public void enterSecurityNumber(String cvv) {
        WebElement securityField = driver.findElement(securityCodeLocator);
        securityField.sendKeys(cvv);
    }

    public void clickOnContinuePayment() {
        this.wait.until(driver -> ExpectedConditions.elementToBeClickable(paymentCountinueBtnLocator).apply(driver)).click();
    }
}
