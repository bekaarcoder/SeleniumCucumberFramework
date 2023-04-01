package org.autozone.pages;

import com.github.javafaker.Faker;
import org.autozone.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Locale;

public class CheckoutPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Faker faker = new Faker(new Locale("en-US"));

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
    private final By firstNameFieldLocator = By.id("firstName");
    private final By lastNameFieldLocator = By.id("lastName");
    private final By phoneNumberFieldLocator = By.id("phoneNumber");
    private final By emailFieldLocator = By.id("email");
    private final By addressFieldLocator = By.id("address1");
    private final By cityFieldLocator = By.id("city");
    private final By stateFieldLocator = By.id("state");
    private final By zipCodeFieldLocator = By.id("zipCode");
    private final By billingContinueBtnLocator = By.id("at_submit_button_address_form");
    private final By completePurchaseBtnLocator = By.xpath("//button[@data-testid='complete-purchase-btn']");
    private final By notificationError = By.xpath("//*[@id='notificationAlert']//span[contains(text(), 'There was a problem with your card')]");

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

    public boolean isBillingAddressEnabled() {
        WebElement billingSection = driver.findElement(billingAddressLocator);
        String isDisabled = billingSection.getAttribute("aria-disabled");
        return !Boolean.parseBoolean(isDisabled);
    }

    public void enterBillingInformation() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = firstName.toLowerCase() + lastName.toLowerCase() + "@mail.com";
        SeleniumUtils.enterKeys(this.driver, firstNameFieldLocator, firstName);
        SeleniumUtils.enterKeys(this.driver, lastNameFieldLocator, lastName);
        SeleniumUtils.enterKeys(this.driver, phoneNumberFieldLocator, faker.phoneNumber().cellPhone());
        SeleniumUtils.enterKeys(this.driver, emailFieldLocator, email);
        SeleniumUtils.enterKeys(this.driver, addressFieldLocator, faker.address().streetAddress());
        SeleniumUtils.enterKeys(this.driver, cityFieldLocator, faker.address().city());
        Select stateDropdown = new Select(driver.findElement(stateFieldLocator));
        stateDropdown.selectByIndex(2);
        SeleniumUtils.enterKeys(this.driver, zipCodeFieldLocator, faker.number().digits(5));
    }

    public void submitBillingInformation() {
        SeleniumUtils.clickElement(this.driver, billingContinueBtnLocator);
    }

    public void completePurchase() {
        SeleniumUtils.waitForElementInvisibility(this.driver, loaderLocator);
        SeleniumUtils.clickElement(this.driver, completePurchaseBtnLocator);
    }

    public boolean isPurchaseUnsuccessful() {
        SeleniumUtils.waitForElementInvisibility(this.driver, loaderLocator);
        return SeleniumUtils.isElementDisplayed(this.driver, notificationError);
    }
}
