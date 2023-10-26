package screens;

import elements.ButtonWrapper;
import elements.LabelWrapper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import screens.components.MessagePopup;
import utils.GestureUtil;

public class LongPressScreen extends BaseScreen<LongPressScreen> {

    private static final Logger logger = LoggerFactory.getLogger(LongPressScreen.class);

    private final ButtonWrapper longPressButton = new ButtonWrapper(driver, AppiumBy.accessibilityId("longpress"));
    private final LabelWrapper instructionLabel =
            new LabelWrapper(driver, AppiumBy.xpath("//android.view.View[@content-desc='longpress']/preceding-sibling::android.widget.TextView"));

    public LongPressScreen(AppiumDriver driver) {
        super(driver, "Long Press Demo");

        logger.info("The Long Press screen initialized");
    }

    @Override
    public boolean isDisplayed() {
        logger.info("Checking if the Long Press screen is displayed");
        return instructionLabel.isDisplayed() && longPressButton.isDisplayed() && headerPanel.isDisplayed();
    }

    public MessagePopup longPressButton() {
        logger.info("Long pressing the button");
        GestureUtil.longPress(driver, longPressButton);
        return new MessagePopup(driver);
    }
}
