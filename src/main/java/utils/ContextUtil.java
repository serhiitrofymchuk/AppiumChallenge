package utils;

import elements.BaseWebElementWrapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextUtil {

    private static final Logger logger = LoggerFactory.getLogger(ContextUtil.class);

    public static final String WEB_CONTEXT = "WEBVIEW";
    public static final String NATIVE_APP_CONTEXT = "NATIVE_APP";

    public static String getCurrentContext(AppiumDriver driver) {
        logger.info("Getting the current context");
        return ((SupportsContextSwitching) driver).getContext();
    }

    public static boolean isWebContextElementDisplayed(AppiumDriver driver, BaseWebElementWrapper element) {
        logger.info("Checking if the following web context element is displayed: {}", element);
        if (NATIVE_APP_CONTEXT.equals(getCurrentContext(driver))) {
            try {
                if (switchToWebContext(driver)) {
                    return element.isDisplayed();
                } else {
                    // Unable to switch the web context to check if the web context element is displayed
                    return false;
                }
            } finally {
                // In any case, back to the native app context
                switchToNativeAppContext(driver);
            }
        } else if (WEB_CONTEXT.equals(getCurrentContext(driver))) {
            return element.isDisplayed();
        } else {
            // Unknown context, unable to check if the web context element is displayed
            return false;
        }
    }

    public static boolean switchToWebContext(AppiumDriver driver) {
        logger.info("Switching to the web context");
        for (String context : ((SupportsContextSwitching) driver).getContextHandles()) {
            if (context.contains(WEB_CONTEXT)) {
                switchTo(driver, WEB_CONTEXT);
                return true;
            }
        }
        return false;
    }

    public static boolean switchToNativeAppContext(AppiumDriver driver) {
        logger.info("Switching to the native app context");
        for (String context : ((SupportsContextSwitching) driver).getContextHandles()) {
            if (context.contains(NATIVE_APP_CONTEXT)) {
                switchTo(driver, NATIVE_APP_CONTEXT);
                return true;
            }
        }
        return false;
    }

    private static void switchTo(AppiumDriver driver, String context) {
        logger.info("The {} context has been found, switching to it", context);
        ((SupportsContextSwitching) driver).context(context);
    }

}
