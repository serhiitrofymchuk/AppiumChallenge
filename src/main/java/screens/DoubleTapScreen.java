package screens;

import elements.ButtonWrapper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import screens.components.MessagePopup;
import utils.GestureUtil;

public class DoubleTapScreen extends BaseScreen<DoubleTapScreen> {

    private static final Logger logger = LoggerFactory.getLogger(DoubleTapScreen.class);

    private final ButtonWrapper doubleTapButton = new ButtonWrapper(driver, AppiumBy.accessibilityId("doubleTapMe"));

    public DoubleTapScreen(AppiumDriver driver) {
        super(driver, "Double Tap Demo");

        logger.info("The Double Tap screen initialized");
    }

    @Override
    public boolean isDisplayed() {
        logger.info("Checking if the Double Tap screen is displayed");
        return doubleTapButton.isDisplayed() && headerPanel.isDisplayed();
    }

    public MessagePopup doubleTapButton() {
        logger.info("Double tapping the button");
        GestureUtil.doubleTap(driver, doubleTapButton);
        return new MessagePopup(driver);
    }
}
