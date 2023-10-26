package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextUtil {

    private static final Logger logger = LoggerFactory.getLogger(ContextUtil.class);

    public static boolean switchToWebContext(AppiumDriver driver) {
        logger.info("Switching to the web context");
        for (String context : ((SupportsContextSwitching) driver).getContextHandles()) {
            if (context.contains("WEBVIEW")) {
                logger.info("The WEBVIEW context has been found, switching to it");
                ((SupportsContextSwitching) driver).context(context);
                return true;
            }
        }
        return false;
    }

    public static boolean switchToNativeAppContext(AppiumDriver driver) {
        logger.info("Switching to the native app context");
        for (String context : ((SupportsContextSwitching) driver).getContextHandles()) {
            if (context.contains("NATIVE_APP")) {
                logger.info("The NATIVE_APP context has been found, switching to it");
                ((SupportsContextSwitching) driver).context(context);
                return true;
            }
        }
        return false;
    }
}
