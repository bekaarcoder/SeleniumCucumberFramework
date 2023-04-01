package org.autozone.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class DriverFactory {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public WebDriver init_driver(String browser) {
        if (browser.equals("chrome")) {
            tlDriver.set(new ChromeDriver());
        } else if (browser.equals("firefox")) {
            FirefoxProfile profile = new FirefoxProfile();
            FirefoxOptions options = new FirefoxOptions();
            profile.setPreference("browser.tabs.remote.autostart.2", false);
            options.setProfile(profile);
            options.addPreference("firefox.commandLineArgs", "--new-instance");
            tlDriver.set(new FirefoxDriver(options));
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
