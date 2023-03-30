package org.autozone.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // By Locators
    private By addVehicleBtnLocator = By.xpath("(//div[@data-testid='add-vehicle-header-btn'])[1]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isHomePage() {
        return this.wait.until(driver -> this.driver.findElement(addVehicleBtnLocator).isDisplayed());
    }

    public void clickOnAddVehicle() {
        WebElement addVehicleBtn = driver.findElement(addVehicleBtnLocator);
        addVehicleBtn.click();
    }
}
