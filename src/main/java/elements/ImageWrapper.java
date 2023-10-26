package elements;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageWrapper extends BaseWebElementWrapper {

    private static final Logger logger = LoggerFactory.getLogger(ImageWrapper.class);

    public ImageWrapper(AppiumDriver driver, By locator) {
        this(driver, locator, null);
    }

    public ImageWrapper(AppiumDriver driver, By locator, BaseWebElementWrapper parent) {
        super(driver, locator, parent);
        logger.debug("The ImageWrapper initialized");
    }

}
