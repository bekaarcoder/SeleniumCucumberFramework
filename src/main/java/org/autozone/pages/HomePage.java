package org.autozone.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public boolean isHomePage() {
        return this.wait.until(driver -> this.driver.findElement(addVehicleBtnLocator).isDisplayed());
    }

    public void clickOnAddVehicle() {
        WebElement addVehicleBtn = driver.findElement(addVehicleBtnLocator);
        addVehicleBtn.click();
    }

    public boolean isAddVehicleModal() {
        WebElement addVehicleModalHeader = driver.findElement(addVehicleHeaderLocator);
        return this.wait.until(driver -> addVehicleModalHeader.isDisplayed());
    }

    public void selectYear(String year) {
        selectField(yearLocator, yearDropdownLocator, year);
    }

    public void selectMake(String make) {
        selectField(makeLocator, makerDropdownLocator, make);
    }

    public void selectModel(String model) {
        selectField(modelLocator, modelDropdownLocator, model);
    }

    private void selectField(By fieldLocator, By dropdownListLocator, String value) {
        WebElement field = driver.findElement(fieldLocator);
        field.sendKeys(value);
        List<WebElement> dropdownLists = driver.findElements(dropdownListLocator);
        this.wait.until(driver -> dropdownLists.size() > 0);
        WebElement dropdownList = dropdownLists.stream().filter(element -> element.getText().trim().equals(value)).findFirst().get();
        dropdownList.click();
    }

    public boolean isVehicleSelected(String vehicle) {
        this.wait.until(driver -> ExpectedConditions.invisibilityOfElementLocated(addVehicleHeaderLocator).apply(driver));
        this.wait.until(driver -> ExpectedConditions.visibilityOfElementLocated(vehicleTextLocator).apply(driver));
        WebElement vehicleTextElement = driver.findElement(vehicleTextLocator);
        return this.wait.until(driver -> {
            boolean isDisplayed = vehicleTextElement.isDisplayed();
            boolean hasCorrectText = vehicleTextElement.getText().trim().equals(vehicle);
            return isDisplayed && hasCorrectText;
        });
    }
}
