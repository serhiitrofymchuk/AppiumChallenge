package steps;

import config.ConfigLoader;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.TimeUtil;

import java.time.Duration;

public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    private static final String BUILD_NAME = TimeUtil.getCurrentTimestamp();

    private static AppiumDriver driver;

    private String scenarioName;

    public static AppiumDriver getDriver() {
        return driver;
    }

    @Before
    public void setUpScenario(Scenario scenario) {
        scenarioName = getScenarioName(scenario);
        logger.info("Starting the scenario: {}", scenarioName);

        logger.info("Setting up an Appium driver");
        driver = DriverFactory.createDriver(BUILD_NAME, scenarioName);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(ConfigLoader.APPIUM_CONFIG.getImplicitWaitTimeout()));
    }

    @After
    public void tearDownScenario(Scenario scenario) {
        logger.info("Ending the scenario: {}", scenarioName);

        if (driver != null) {

            if (scenario.isFailed()) {
                logger.info("Taking a screenshot of the failed scenario");
                final byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenarioName);
            }

            logger.info("Quitting the Appium driver");
            driver.quit();
        }
    }

    private String getScenarioName(Scenario scenario) {
        return String.join(" ", scenario.getSourceTagNames()) + " - " + scenario.getName();
    }
}
