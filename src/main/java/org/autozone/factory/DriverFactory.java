package org.autozone.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    public WebDriver driver;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public WebDriver init_driver(String browser) {
        if (browser.equals("chrome")) {
            tlDriver.set(new ChromeDriver());
        } else if (browser.equals("firefox")) {
            tlDriver.set(new FirefoxDriver());
        } else {
            tlDriver.set(new ChromeDriver());
        }

        getDriver().manage().window().maximize();
        return getDriver();
    }

    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }
}
