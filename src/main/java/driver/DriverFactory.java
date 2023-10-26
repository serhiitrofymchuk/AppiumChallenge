package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import config.ConfigLoader;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);


    public static AppiumDriver createDriver() {
        logger.info("Creating an Appium driver");
        String platformName = ConfigLoader.APPIUM_CONFIG.getPlatformName();
        if ("Android".equalsIgnoreCase(platformName)) {
            return createAndroidDriver();
        } else if ("iOS".equalsIgnoreCase(platformName)) {
            return createIOSDriver();
        } else {
            throw new AssertionError(String.format("Unsupported platform: %s", platformName));
        }
    }

    private static AndroidDriver createAndroidDriver() {
        try {
            logger.info("Creating an Android driver");
            ConfigLoader config = ConfigLoader.APPIUM_CONFIG;
            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName(config.getPlatformName())
                    .setPlatformVersion(config.getPlatformVersion())
                    .setDeviceName(config.getDeviceName())
                    .setApp(config.getAppLocation());
            return new AndroidDriver(new URL(config.getServerUrl()), options);
        } catch (MalformedURLException e) {
            throw new AssertionError("Error creating the Android driver", e);
        }
    }

    private static IOSDriver createIOSDriver() {
        try {
            logger.info("Creating an iOS driver");
            ConfigLoader config = ConfigLoader.APPIUM_CONFIG;
            XCUITestOptions options = new XCUITestOptions()
                    .setPlatformName(config.getPlatformName())
                    .setPlatformVersion(config.getPlatformVersion())
                    .setDeviceName(config.getDeviceName())
                    .setApp(config.getAppLocation());
            return new IOSDriver(new URL(config.getServerUrl()), options);
        } catch (MalformedURLException e) {
            throw new AssertionError("Error creating the iOS driver", e);
        }
    }
}
