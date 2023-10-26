package utils;

import config.ConfigLoader;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class WaitUtil {

    private static final Logger logger = LoggerFactory.getLogger(WaitUtil.class);

    public static WebDriverWait getWait(AppiumDriver driver) {
        Duration defaultDuration = Duration.ofMillis(ConfigLoader.APPIUM_CONFIG.getExplicitWaitTimeout());
        logger.info("Getting WebDriverWait with the default explicit wait timeout (in seconds): {}",
                TimeUtil.durationToSeconds(defaultDuration));
        return new WebDriverWait(driver, defaultDuration);
    }

    public static WebDriverWait getWait(AppiumDriver driver, Duration duration) {
        logger.info("Getting WebDriverWait with the following timeout (in seconds): {}", TimeUtil.durationToSeconds(duration));
        return new WebDriverWait(driver, duration);
    }

    public static void waitFor(Duration duration) {
        logger.info("Waiting for the duration of: {}", duration);
        try {
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            throw new AssertionError("Unable to wait for the duration of: " + duration, e);
        }
    }
}
