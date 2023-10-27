package screens;

import elements.LabelWrapper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ContextUtil;

public class WebViewScreen extends BaseScreen<WebViewScreen> {

    private static final Logger logger = LoggerFactory.getLogger(WebViewScreen.class);

    private final LabelWrapper hackerNewsTitleLabel = new LabelWrapper(driver, AppiumBy.xpath("//a[text()='Hacker News']"));

    public WebViewScreen(AppiumDriver driver) {
        super(driver, "Webview");

        logger.info("The Web View screen initialized");
    }

    @Override
    public boolean isDisplayed() {
        logger.info("Checking if the Web View screen is displayed");
        return headerPanel.isDisplayed() && ContextUtil.isWebContextElementDisplayed(driver, hackerNewsTitleLabel);
    }

    public boolean isHackerNewsWebContentDisplayed() {
        logger.info("Checking if the Hacker News web content is displayed");
        return ContextUtil.isWebContextElementDisplayed(driver, hackerNewsTitleLabel);
    }

}
