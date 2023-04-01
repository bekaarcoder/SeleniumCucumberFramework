## Cucumber TestNG Framework using Java and Selenium WebDriver

This automation framework is created using Cucumber and TestNG following the BDD approach.
The project demonstrates how to setup and configure the testing framework and execute the tests in parallel
using TestNG.
The framework is also cross-browser compatible. (Runs on chrome and firefox)

## Prerequisites
- Java JDK version 8 or higher
- Maven
- IDE (Eclipse or IntelliJ IDEA)
- Chrome/Firefox

## Dependencies Used
- cucumber-java: 7.11.2 
- cucumber-testng: 7.11.2
- selenium-java: 4.8.3
- testng: 7.7.1
- faker: 0.12
- maven-surefire-plugin: 2.20

## Project Structure
The project structure is designed following the POM approach.
It consists of the following directories:

1. **src/test/java:** Contains cucumber hooks, step definitions and TestRunner class. 

2. **src/test/resources:** Contains all the required resources for test automation, such as feature files, configuration files and testng.xml file.

3. **src/main/java:** Contains all the test automation code, such as Page class files, utilities and DriverFactory class to initialize the webdriver.

## Installation
1. Clone the repository or download the source code as a zip file and extract it.
2. Open the project in your preferred IDE.
3. Import the required dependencies using Maven.
4. Run the test using the testng.xml file.

## Running the Tests
To run the tests, follow the steps below:

1. Open the testng.xml file located in the `src/test/resources` directory of the project.
2. Right-click on the file and run.

The tests will start running and the results will be displayed in the console.

## Report Generation
Report will be generated once all the test has been executed.
TestRunner class is configured to generate the cucumber html report in `target/cucumber-reports.html`.

Also 'cucumber.publish.enabled=true' has been configured in the `cucumber.properties` file to publish the cucumber report.

## Screenshot on Failure
The framework takes the screenshot of the webpage if the scenario has failed.
The screenshot is attached to the cucumber report at the failure step.

## Cross Browser Support
The framework support cross browser testing.
Browser can be configured in the `configuration.properties` file located in the `src/test/resources/config` directory.