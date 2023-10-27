package driver;

import config.ConfigLoader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.ScreenOrientation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    public static AppiumDriver createDriver(String buildName, String scenarioName) {
        logger.info("Creating an Appium driver");
        String platformName = ConfigLoader.APPIUM_CONFIG.getPlatformName();
        if ("Android".equalsIgnoreCase(platformName)) {
            return createAndroidDriver(buildName, scenarioName);
        } else {
            throw new AssertionError("Unsupported platform: " + platformName);
        }
    }

    private static AndroidDriver createAndroidDriver(String buildName, String scenarioName) {
        try {
            logger.info("Creating an Android driver");
            ConfigLoader config = ConfigLoader.APPIUM_CONFIG;

            logger.info("Setting the UiAutomator2 options");
            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName(config.getPlatformName())
                    .setPlatformVersion(config.getPlatformVersion())
                    .setDeviceName(config.getDeviceName())
                    .setOrientation(ScreenOrientation.PORTRAIT);

            if (config.getServerUrl().contains("saucelabs")) {
                // The valid SauceLabs app property should be provided
                options.setApp(config.getApp());

                options.setCapability("sauce:options", getSauceOptions(config, buildName, scenarioName));
            } else {
                // Absolute path of the APK file should be provided for the local Appium server instance
                options.setApp(System.getProperty("user.dir") + "/src/test/resources/" + config.getApp());
            }

            return new AndroidDriver(new URL(config.getServerUrl()), options);
        } catch (MalformedURLException e) {
            throw new AssertionError("Error creating the Android driver", e);
        }
    }

    private static MutableCapabilities getSauceOptions(ConfigLoader config, String buildName, String scenarioName) {
        logger.info("Setting the SauceLabs options");
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("appiumVersion", config.getSauceLabsAppiumVersion());
        sauceOptions.setCapability("username", config.getSauceLabsUsername());
        sauceOptions.setCapability("accessKey", config.getSauceLabsAccessKey());
        sauceOptions.setCapability("build", buildName);
        sauceOptions.setCapability("name", scenarioName);
        return sauceOptions;
    }

}
