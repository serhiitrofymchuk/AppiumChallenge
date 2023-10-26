package elements;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ButtonWrapper extends BaseWebElementWrapper {

    private static final Logger logger = LoggerFactory.getLogger(ButtonWrapper.class);

    public ButtonWrapper(AppiumDriver driver, By locator) {
        this(driver, locator, null);
    }

    public ButtonWrapper(AppiumDriver driver, By locator, BaseWebElementWrapper parent) {
        super(driver, locator, parent);
        logger.debug("The ButtonWrapper initialized");
    }

}
