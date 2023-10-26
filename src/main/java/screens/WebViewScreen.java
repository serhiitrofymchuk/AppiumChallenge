package screens;

import elements.LabelWrapper;
import elements.ViewWrapper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ContextUtil;
import utils.WaitUtil;

public class WebViewScreen extends BaseScreen<WebViewScreen> {

    private static final Logger logger = LoggerFactory.getLogger(WebViewScreen.class);

    private final ViewWrapper webView = new ViewWrapper(driver, AppiumBy.accessibilityId("Web View"));

    private final LabelWrapper hackerNewsTitleLabel =
            new LabelWrapper(driver, AppiumBy.xpath("//b[@class='hnname']/a[text()='Hacker News']"));

    public WebViewScreen(AppiumDriver driver) {
        super(driver, "Webview");

        logger.info("The Web View screen initialized");
    }

    @Override
    public boolean isDisplayed() {
        logger.info("Checking if the Web View screen is displayed");
        return webView.isDisplayed() && headerPanel.isDisplayed();
    }

    public boolean isHackerNewsWebContentDisplayed() {
        logger.info("Checking if the Hacker News web content is displayed");
        boolean isDisplayed = false;

        try {
            // If switching to the Web context successful then we can work with it
            if (ContextUtil.switchToWebContext(driver)) {

                // Wait for the web context element is accessible
                WaitUtil.getWait(driver)
                        .withMessage("The Hacker News web content did not appear")
                        .until(ExpectedConditions.visibilityOfElementLocated(hackerNewsTitleLabel.getLocator()));

                // Retrieve the result
                isDisplayed = hackerNewsTitleLabel.isDisplayed();
            }
        } finally {
            // Switch back to the Native App context ANYWAY
            ContextUtil.switchToNativeAppContext(driver);
        }

        return isDisplayed;
    }
}
