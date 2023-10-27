package elements;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtil;

import java.util.List;

public class DropdownWrapper extends BaseWebElementWrapper {

    private static final Logger logger = LoggerFactory.getLogger(DropdownWrapper.class);

    private final By selectedOptionLocator;
    private final By optionsLocator;

    private WebElement selectedOptionWebElement;
    private List<WebElement> optionWebElements;

    public DropdownWrapper(AppiumDriver driver,
                           By locator,
                           By selectedOptionLocator,
                           By optionsLocator) {
        this(driver, locator, selectedOptionLocator, optionsLocator, null);
    }

    public DropdownWrapper(AppiumDriver driver,
                           By locator,
                           By selectedOptionLocator,
                           By optionsLocator,
                           BaseWebElementWrapper parent) {
        super(driver, locator, parent);
        this.selectedOptionLocator = selectedOptionLocator;
        this.optionsLocator = optionsLocator;
        logger.debug("The DropdownWrapper initialized");
    }

    public WebElement getSelectedOptionWebElement() {
        logger.debug("Getting the selected option WebElement");
        if (selectedOptionWebElement == null) {
            initSelectedOptionWebElement();
        }
        return selectedOptionWebElement;
    }

    public void resetSelectedOptionWebElement() {
        logger.debug("Resetting the selected option WebElement to NULL. It will be re-initialized using the locator by the next getSelectedOptionWebElement() invocation");
        selectedOptionWebElement = null;
    }

    public List<WebElement> getOptionWebElements() {
        logger.debug("Getting the option WebElements");
        if (optionWebElements == null) {
            initOptionWebElements();
        }
        return optionWebElements;
    }

    public void resetOptionWebElements() {
        logger.debug("Resetting the option WebElements to NULL. They will be re-initialized using the locator by the next getOptionWebElements() invocation");
        optionWebElements = null;
    }

    public String getSelectedOption() {
        logger.debug("Getting the selected option");
        return getSelectedOptionWebElement().getText();
    }

    public DropdownWrapper openDropdown() {
        logger.debug("Opening the dropdown");
        getWebElement().click();

        WaitUtil.getWait(driver)
                .withMessage("The Dropdown did not open")
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(optionsLocator));

        resetOptionWebElements();
        return this;
    }

    public DropdownWrapper selectOption(String option) {
        logger.debug("Selecting the option: {}", option);
        WebElement optionWebElement = getOptionWebElements().stream()
                .filter(webElement -> webElement.getText().equals(option))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Unable to find the Dropdown option: " + option));

        optionWebElement.click();

        WaitUtil.getWait(driver)
                .withMessage("The Dropdown did not close after the option selection")
                .until(ExpectedConditions.invisibilityOfElementLocated(optionsLocator));

        resetWebElement();
        resetSelectedOptionWebElement();
        return this;
    }

    @Override
    public String toString() {
        return super.toString() + "\nAnd with the selected option locator: " + selectedOptionLocator
                + "\nAnd with the options locator: " + optionsLocator;
    }

    private void initSelectedOptionWebElement() {
        logger.debug("Initializing the selected option WebElement");
        if (parentWebElementWrapper == null) {
            selectedOptionWebElement = driver.findElement(selectedOptionLocator);
        } else {
            selectedOptionWebElement = parentWebElementWrapper.getWebElement().findElement(selectedOptionLocator);
        }
    }

    private void initOptionWebElements() {
        logger.debug("Initializing the option WebElements");
        if (parentWebElementWrapper == null) {
            optionWebElements = driver.findElements(optionsLocator);
        } else {
            optionWebElements = parentWebElementWrapper.getWebElement().findElements(optionsLocator);
        }
    }
}
