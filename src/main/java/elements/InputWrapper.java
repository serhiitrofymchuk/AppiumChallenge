package elements;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputWrapper extends BaseWebElementWrapper {

    private static final Logger logger = LoggerFactory.getLogger(InputWrapper.class);

    public InputWrapper(AppiumDriver driver, By locator) {
        this(driver, locator, null);
    }

    public InputWrapper(AppiumDriver driver, By locator, BaseWebElementWrapper parent) {
        super(driver, locator, parent);
        logger.debug("The InputWrapper initialized");
    }

    public void enter(String text) {
        logger.debug("Entering the text: {}", text);
        getWebElement().sendKeys(text);
    }

}
