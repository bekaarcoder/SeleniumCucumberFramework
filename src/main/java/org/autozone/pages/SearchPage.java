package org.autozone.pages;

import org.autozone.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        return SeleniumUtils.isElementDisplayed(this.driver, searchPageHeaderLocator);
    }

    public boolean isSearchListAvailable() {
        return SeleniumUtils.isElementDisplayed(this.driver, searchListLocator);
    }

    public void navigateToProduct() {
        SeleniumUtils.waitForElementVisibility(this.driver, searchListLocator);
        List<WebElement> searchLists = driver.findElements(searchListsLocator);
        this.wait.until(driver -> searchLists.size() > 0);
        SeleniumUtils.clickElement(this.driver, searchLists.get(0));
    }
}
