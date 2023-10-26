package steps;

import io.cucumber.java.en.Given;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.AppUtil;
import config.ConfigLoader;

public class AppSteps {

    private static final Logger logger = LoggerFactory.getLogger(AppSteps.class);

    @Given("the app is installed")
    public void theAppIsInstalled() {
        logger.info("Installing the app");
        AppUtil.installApp(Hooks.getDriver(), ConfigLoader.APPIUM_CONFIG.getAppLocation());
    }

    @Given("the app is active")
    public void theAppIsActive() {
        logger.info("Activating the app");
        AppUtil.activateApp(Hooks.getDriver());
    }

}
