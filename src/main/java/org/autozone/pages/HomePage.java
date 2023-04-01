package org.autozone.pages;

import org.autozone.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // By Locators
    private final By addVehicleBtnLocator = By.xpath("(//div[@data-testid='add-vehicle-header-btn'])[1]");
    private final By addVehicleHeaderLocator = By.id("workingOnModal");
    private final By yearLocator = By.id("yearheader");
    private final By makeLocator = By.id("makeheader");
    private final By modelLocator = By.id("modelheader");
    private final By yearDropdownLocator = By.xpath("//div[@data-testid='yearheader-dropdown-list']//button");
    private final By makerDropdownLocator = By.xpath("//div[@data-testid='makeheader-dropdown-list']//button");
    private final By modelDropdownLocator = By.xpath("//div[@data-testid='modelheader-dropdown-list']//button");
    private final By vehicleTextLocator = By.xpath("(//div[@data-testid='vehicle-text'])[1]");
    private final By searchFieldLocator = By.xpath("//input[@data-testid='desktop-search-input']");
    private final By searchBtnLocator = By.id("searchBtnDesktopAndTablet");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public boolean isHomePage() {
        return SeleniumUtils.isElementDisplayed(this.driver, addVehicleBtnLocator);
    }

    public void clickOnAddVehicle() {
        SeleniumUtils.clickElement(this.driver, addVehicleBtnLocator);
    }

    public boolean isAddVehicleModal() {
        return SeleniumUtils.isElementDisplayed(this.driver, addVehicleHeaderLocator);
    }

    public void selectYear(String year) throws InterruptedException {
        selectField(yearLocator, yearDropdownLocator, year);
    }

    public void selectMake(String make) throws InterruptedException {
        selectField(makeLocator, makerDropdownLocator, make);
    }

    public void selectModel(String model) throws InterruptedException {
        selectField(modelLocator, modelDropdownLocator, model);
    }

    public boolean isVehicleSelected(String vehicle) {
        SeleniumUtils.waitForElementInvisibility(this.driver, addVehicleHeaderLocator);
        SeleniumUtils.waitForElementVisibility(this.driver, vehicleTextLocator);
        WebElement vehicleTextElement = driver.findElement(vehicleTextLocator);
        return this.wait.until(driver -> {
            boolean isDisplayed = vehicleTextElement.isDisplayed();
            boolean hasCorrectText = vehicleTextElement.getText().trim().equals(vehicle);
            return isDisplayed && hasCorrectText;
        });
    }

    public void enterSearchKeyword(String keyword) {
        SeleniumUtils.enterKeys(this.driver, searchFieldLocator, keyword);
    }

    public void clickOnSearch() {
        SeleniumUtils.clickElement(this.driver, searchBtnLocator);
    }

    private void selectField(By fieldLocator, By dropdownListLocator, String value) throws InterruptedException {
        WebElement field = driver.findElement(fieldLocator);
        field.sendKeys(value);
        Thread.sleep(1000);
        List<WebElement> dropdownLists = driver.findElements(dropdownListLocator);
        this.wait.until(driver -> dropdownLists.size() > 0);
        WebElement dropdownList = dropdownLists.stream().filter(element -> element.getText().trim().equals(value)).findFirst().get();
        dropdownList.click();
    }
}
