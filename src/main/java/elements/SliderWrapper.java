package elements;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.GestureUtil;

public class SliderWrapper extends BaseWebElementWrapper {

    private static final Logger logger = LoggerFactory.getLogger(SliderWrapper.class);

    private final By positionLocator;

    private WebElement positionWebElement;

    public SliderWrapper(AppiumDriver driver, By locator, By positionLocator) {
        this(driver, locator, positionLocator, null);
    }

    public SliderWrapper(AppiumDriver driver, By locator, By positionLocator, BaseWebElementWrapper parent) {
        super(driver, locator, parent);
        this.positionLocator = positionLocator;
        logger.debug("The SliderWrapper initialized");
    }

    public WebElement getPositionWebElement() {
        logger.debug("Getting the position WebElement");
        if (positionWebElement == null) {
            initPositionWebElement();
        }
        return positionWebElement;
    }

    public void resetPositionWebElement() {
        logger.debug("Resetting the position WebElement to NULL. It will be re-initialized using the locator by the next getPositionWebElement() invocation");
        positionWebElement = null;
    }

    public int getPosition() {
        try {
            logger.debug("Getting the position");
            return Integer.parseInt(getPositionWebElement().getText());
        } catch (NumberFormatException e) {
            throw new AssertionError("Unable to get the Slider position");
        }
    }

    public SliderWrapper move(int percent) {
        logger.debug("Moving the slider by {}% to the {}", percent, percent > 0 ? "right" : "left");
        GestureUtil.swipeOverElementHorizontally(driver, this, percent);
        return this;
    }

    @Override
    public String toString() {
        return super.toString() + "\nAnd with the position locator: " + positionLocator;
    }

    private void initPositionWebElement() {
        logger.debug("Initializing the position WebElement");
        positionWebElement = driver.findElement(positionLocator);
    }

}
