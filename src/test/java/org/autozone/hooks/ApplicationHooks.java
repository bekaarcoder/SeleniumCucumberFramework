package org.autozone.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.autozone.factory.DriverFactory;
import org.autozone.utils.ConfigReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String scenarioName = scenario.getName().replace(" ", "_");
            byte[] sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", scenarioName);
        }
        driver.quit();
    }
}
