package screens.components;

import elements.ButtonWrapper;
import elements.LabelWrapper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtil;

public class MessagePopup extends BaseComponent {

    private static final Logger logger = LoggerFactory.getLogger(MessagePopup.class);

    private final LabelWrapper titleLabel = new LabelWrapper(driver, AppiumBy.id("android:id/alertTitle"));
    private final LabelWrapper messageLabel = new LabelWrapper(driver, AppiumBy.id("android:id/message"));
    private final ButtonWrapper okButton = new ButtonWrapper(driver, AppiumBy.id("android:id/button1"));

    public MessagePopup(AppiumDriver driver) {
        super(driver);

        logger.info("The Message Popup initialized");
    }

    @Override
    public boolean isDisplayed() {
        logger.info("Checking if the Message Popup is displayed");
        return titleLabel.isDisplayed() && messageLabel.isDisplayed() && okButton.isDisplayed();
    }

    public boolean isPresent() {
        logger.info("Checking if the Message Popup is present");
        return titleLabel.isPresent() && messageLabel.isPresent() && okButton.isPresent();
    }

    public String getTitleLabelText() {
        logger.info("Getting the Title label's text");
        return titleLabel.getText();
    }

    public String getMessageLabelText() {
        logger.info("Getting the Message label's text");
        return messageLabel.getText();
    }

    public MessagePopup tapOkButton() {
        logger.info("Taping the Ok button");
        okButton.tap();
        return this;
    }

    public MessagePopup waitToDisappear() {
        logger.info("Waiting for the Message Popup to disappear");
        WaitUtil.getWait(driver).withMessage("The Message Popup did not disappear")
                .until(ExpectedConditions.invisibilityOfAllElements(
                        titleLabel.getWebElement(),
                        messageLabel.getWebElement(),
                        okButton.getWebElement()));
        titleLabel.resetWebElement();
        messageLabel.resetWebElement();
        okButton.resetWebElement();
        return this;
    }
}
