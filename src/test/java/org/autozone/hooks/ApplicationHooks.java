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
        ConfigReader configReader = new ConfigReader();
        properties = configReader.init_prop();
    }

    @Before(order = 1)
    public void launchBrowser() {
        String browser = properties.getProperty("browser");
        DriverFactory driverFactory = new DriverFactory();
        driver = driverFactory.init_driver(browser);
    }

    @After(order = 1)
    public void tearDown() {
        driver.quit();
    }
}
