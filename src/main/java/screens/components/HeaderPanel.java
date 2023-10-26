package screens.components;

import elements.ButtonWrapper;
import elements.LabelWrapper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import screens.BaseScreen;
import screens.LoginScreen;
import screens.SamplesListScreen;
import utils.WaitUtil;

public class HeaderPanel<T extends BaseScreen<?>> extends BaseComponent {

    private static final Logger logger = LoggerFactory.getLogger(HeaderPanel.class);

    private final T parentScreen;

    private final LabelWrapper titleLabel;
    private final ButtonWrapper backButton;

    private final String screenTitle;

    public HeaderPanel(AppiumDriver driver, T parentScreen, String screenTitle) {
        super(driver);
        this.parentScreen = parentScreen;
        this.screenTitle = screenTitle;

        // There is no other possibility to locate the title, only by its text :(
        this.titleLabel = new LabelWrapper(driver, AppiumBy.xpath("//*[@text='" + screenTitle + "']"));
        this.backButton = new ButtonWrapper(driver, AppiumBy.xpath("//*[@text='Back']"));

        logger.info("The '{}' Header Panel initialized", screenTitle);
    }

    @Override
    public boolean isDisplayed() {
        logger.info("Checking if the '{}' Header Panel is displayed", screenTitle);

        WaitUtil.getWait(driver)
                .withMessage("The Header Panel with the '" + screenTitle + "' required title did not appear")
                .until(ExpectedConditions.presenceOfElementLocated(titleLabel.getLocator()));

        return titleLabel.isDisplayed() && backButton.isDisplayed();
    }

    public BaseScreen<?> tapBackButton() {
        logger.info("Taping the Back button");
        backButton.tap();

        if (parentScreen instanceof LoginScreen || parentScreen instanceof SamplesListScreen) {
            return new LoginScreen(driver);
        } else {
            return new SamplesListScreen(driver);
        }
    }
}
