package org.autozone.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By searchPageHeaderLocator = By.xpath("//h1[@data-testid='search-results-thin-header']");
    private final By searchListLocator = By.id("search-result-list");
    private final By searchListsLocator = By.xpath("//div[@id='search-result-list']//a");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public boolean isSearchPage() {
        return this.wait.until(driver -> ExpectedConditions.visibilityOfElementLocated(searchPageHeaderLocator).apply(driver)).isDisplayed();
    }

    public boolean isSearchListAvailable() {
        return this.wait.until(driver -> ExpectedConditions.visibilityOfElementLocated(searchListLocator).apply(driver)).isDisplayed();
    }

    public void navigateToProduct() {
        List<WebElement> searchLists = driver.findElements(searchListsLocator);
        this.wait.until(driver -> searchLists.size() > 0);
        searchLists.get(0).click();
    }
}
