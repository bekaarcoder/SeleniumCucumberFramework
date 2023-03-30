package org.autozone.pages;

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
    private final By yearDropdownLocator = By.xpath("//div[@data-testid='yearheader-dropdown-list']//button");

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
        WebElement yearField = driver.findElement(yearLocator);
        yearField.sendKeys(year);
        List<WebElement> yearDropDownLists = driver.findElements(yearDropdownLocator);
        this.wait.until(driver -> yearDropDownLists.size() > 0);
        WebElement yearList = yearDropDownLists.stream().filter(element -> element.getText().trim().equals(year)).findFirst().get();
        yearList.click();
    }
}
