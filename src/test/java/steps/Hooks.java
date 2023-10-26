package steps;

import config.ConfigLoader;
import driver.DriverFactory;
import driver.ServerFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Hooks {

    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    private static AppiumDriverLocalService server;
    private static AppiumDriver driver;

    public static AppiumDriver getDriver() {
        return driver;
    }

    @BeforeAll
    public static void setUpTestRun() {
        logger.info("Starting an Appium server");
        server = ServerFactory.getServer();
        server.start();
        logger.info("The Appium server started successfully on {}", server.getUrl());
    }

    @AfterAll
    public static void tearDownTestRun() {
        if (server != null && server.isRunning()) {
            logger.info("Stopping the Appium server");
            server.stop();
        }
    }

    @Before
    public void setUpScenario() {
        logger.info("Setting up an Appium driver");
        driver = DriverFactory.createDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(ConfigLoader.APPIUM_CONFIG.getImplicitWaitTimeout()));
    }

    @After
    public void tearDownScenario(Scenario scenario) {
        if (driver != null) {
            if (scenario.isFailed()) {
                logger.info("Taking a screenshot of the failed scenario");
                final byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            }

            logger.info("Quitting the Appium driver");
            driver.quit();
        }
    }

}
