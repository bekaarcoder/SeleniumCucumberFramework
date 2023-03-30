package org.autozone.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.autozone.factory.DriverFactory;
import org.autozone.utils.ConfigReader;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class ApplicationHooks {
    private WebDriver driver;
    Properties properties;

    @Before(order = 0)
    public void getProperty() {
        System.out.println("Getting Properties");
        ConfigReader configReader = new ConfigReader();
        properties = configReader.init_prop();
        System.out.println(properties.getProperty("browser"));
    }

    @Before(order = 1)
    public void launchBrowser() {
        System.out.println("Getting Browser");
        String browser = properties.getProperty("browser");
        System.out.println("Browser: " + browser);
        DriverFactory driverFactory = new DriverFactory();
        driver = driverFactory.init_driver(browser);
    }

    @After(order = 1)
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
    }
}
