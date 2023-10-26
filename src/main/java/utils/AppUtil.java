package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.app.SupportsAppPackageOption;
import io.appium.java_client.appmanagement.ApplicationState;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.app.SupportsBundleIdOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppUtil {

    private static final Logger logger = LoggerFactory.getLogger(AppUtil.class);

    public static void installApp(AppiumDriver driver, String appPath) {
        logger.debug("Installing the app");
        if (driver == null) {
            throw new AssertionError("The Appium driver is not initialized");
        }
        String appId = getAppId(driver);
        if (!((InteractsWithApps) driver).isAppInstalled(appId)) {
            ((InteractsWithApps) driver).installApp(appPath);
        }
    }

    public static void activateApp(AppiumDriver driver) {
        logger.debug("Activating the app");
        if (driver == null) {
            throw new AssertionError("The Appium driver is not initialized");
        }
        String appId = getAppId(driver);
        ((InteractsWithApps) driver).activateApp(appId);
    }

    public static void terminateApp(AppiumDriver driver) {
        logger.debug("Terminating the app");
        if (driver == null) {
            throw new AssertionError("The Appium driver is not initialized");
        }
        String appId = getAppId(driver);
        ((InteractsWithApps) driver).terminateApp(appId);
    }

    public static boolean appHasState(AppiumDriver driver, ApplicationState state) {
        logger.debug("Checking if the app has the following state: {}", state);
        if (driver == null) {
            throw new AssertionError("The Appium driver is not initialized");
        }
        String appId = getAppId(driver);
        return ((InteractsWithApps) driver).queryAppState(appId).equals(state);
    }

    private static String getAppId(AppiumDriver driver) {
        logger.debug("Getting the app's Id from the Appium driver");
        if (driver == null) {
            throw new AssertionError("The Appium driver is not initialized");
        }
        String appId;
        if (driver instanceof AndroidDriver) {
            appId = (String) driver.getCapabilities().getCapability(SupportsAppPackageOption.APP_PACKAGE_OPTION);
        } else if (driver instanceof IOSDriver) {
            appId = (String) driver.getCapabilities().getCapability(SupportsBundleIdOption.BUNDLE_ID_OPTION);
        } else {
            throw new AssertionError("Unsupported type of the Appium driver");
        }
        logger.debug("The app's Id: {}", appId);
        return appId;
    }

}
