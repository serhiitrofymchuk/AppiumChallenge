package screens;

import elements.LabelWrapper;
import elements.ViewWrapper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NativeViewScreen extends BaseScreen<NativeViewScreen> {

    private static final Logger logger = LoggerFactory.getLogger(NativeViewScreen.class);

    private final ViewWrapper oneView = new ViewWrapper(driver, AppiumBy.accessibilityId("container1"));
    private final ViewWrapper twoView = new ViewWrapper(driver, AppiumBy.accessibilityId("container2"));
    private final ViewWrapper threeView = new ViewWrapper(driver, AppiumBy.accessibilityId("container3"));

    public NativeViewScreen(AppiumDriver driver) {
        super(driver, "Native View Demo");
        logger.info("The Native View screen initialized");
    }

    @Override
    public boolean isDisplayed() {
        logger.info("Checking if the Native View screen is displayed");
        return oneView.isDisplayed()
                && twoView.isDisplayed()
                && threeView.isDisplayed()
                && headerPanel.isDisplayed();
    }

    public String getOneViewText() {
        logger.info("Getting the View One's text");
        return getChildLabel(oneView).getText();
    }

    public String getTwoViewText() {
        logger.info("Getting the View Two's text");
        return getChildLabel(twoView).getText();
    }

    public String getThreeViewText() {
        logger.info("Getting the View Three's text");
        return getChildLabel(threeView).getText();
    }

    private LabelWrapper getChildLabel(ViewWrapper view) {
        return new LabelWrapper(driver, AppiumBy.accessibilityId("textView"), view);
    }
}
