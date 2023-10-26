package elements;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class BaseWebElementWrapper {

    private static final Logger logger = LoggerFactory.getLogger(BaseWebElementWrapper.class);

    protected final AppiumDriver driver;
    protected final By locator;
    protected final BaseWebElementWrapper parentWebElementWrapper;

    private WebElement webElement;

    protected BaseWebElementWrapper(AppiumDriver driver, By locator, BaseWebElementWrapper parentWebElementWrapper) {
        this.driver = driver;
        this.locator = locator;
        this.parentWebElementWrapper = parentWebElementWrapper;
        logger.debug("The BaseElementWrapper initialized");
    }

    public By getLocator() {
        logger.debug("Getting the locator");
        return locator;
    }

    public WebElement getWebElement() {
        logger.debug("Getting the WebElement");
        if (webElement == null) {
            initWebElement();
        }
        return webElement;
    }

    public void resetWebElement() {
        logger.debug("Resetting the WebElement to NULL. It will be re-initialized using the locator by the next getWebElement() invocation");
        webElement = null;
    }

    public BaseWebElementWrapper getParentWebElementWrapper() {
        logger.debug("Getting the parent WebElement wrapper");
        return parentWebElementWrapper;
    }

    public boolean isPresent() {
        logger.debug("Checking if the WebElement is present");
        return parentWebElementWrapper == null ?
                !driver.findElements(locator).isEmpty() :
                parentWebElementWrapper.isPresent() && !parentWebElementWrapper.getWebElement().findElements(locator).isEmpty();
    }

    public boolean isNotPresent() {
        return !isPresent();
    }

    public boolean isDisplayed() {
        logger.debug("Checking if the WebElement is displayed");
        return getWebElement().isDisplayed();
    }

    public boolean isNotDisplayed() {
        return !isDisplayed();
    }

    public String getText() {
        logger.debug("Getting the WebElement's text");
        return getWebElement().getText();
    }

    public Point getLocation() {
        logger.debug("Getting the WebElement's location");
        return getWebElement().getLocation();
    }

    public Dimension getSize() {
        logger.debug("Getting the WebElement's size");
        return getWebElement().getSize();
    }

    public WebElement getChildWebElement(By locator) {
        logger.debug("Getting a child WebElement using the locator: {}", locator);
        return getWebElement().findElement(locator);
    }

    public List<WebElement> getChildWebElements(By locator) {
        logger.debug("Getting child WebElements using the locator: {}", locator);
        return getWebElement().findElements(locator);
    }

    public void tap() {
        logger.debug("Tapping the WebElement");
        getWebElement().click();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " with the locator: " + locator
                + (parentWebElementWrapper == null ? "" : "\nAnd with the parent WebElement wrapper:\n" + parentWebElementWrapper);
    }

    public void initWebElement() {
        logger.debug("Initializing the WebElement");
        webElement = parentWebElementWrapper == null ?
                driver.findElement(locator) :
                parentWebElementWrapper.getWebElement().findElement(locator);
    }
}
