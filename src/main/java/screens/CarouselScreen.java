package screens;

import elements.LabelWrapper;
import elements.ViewWrapper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.GestureUtil;

public class CarouselScreen extends BaseScreen<CarouselScreen> {

    private static final Logger logger = LoggerFactory.getLogger(CarouselScreen.class);

    private final ViewWrapper blockView = new ViewWrapper(driver,
            AppiumBy.xpath("//android.widget.HorizontalScrollView//android.widget.TextView/.."));
    private final LabelWrapper blockLabel = new LabelWrapper(driver,
            AppiumBy.xpath("//android.widget.HorizontalScrollView//android.widget.TextView"));

    private final LabelWrapper pageNumberLabel = new LabelWrapper(driver,
            AppiumBy.xpath("//android.widget.HorizontalScrollView/following-sibling::*//android.widget.TextView"));

    public CarouselScreen(AppiumDriver driver) {
        super(driver, "Carousel - Swipe left/right");

        logger.info("The Carousel screen initialized");
    }

    @Override
    public boolean isDisplayed() {
        logger.info("Checking if the Carousel screen is displayed");
        return blockView.isDisplayed() && blockLabel.isDisplayed() && pageNumberLabel.isDisplayed() && headerPanel.isDisplayed();
    }

    public CarouselScreen swipeBlock(boolean left) {
        logger.info("Swiping the current block to the {}", left ? "left" : "right");
        GestureUtil.swipeElementHorizontally(driver, blockView, left);
        resetDynamicElements();
        return this;
    }

    public int getBlockNumber() {
        logger.info("Getting the number of the current block");
        try {
            return Integer.parseInt(blockLabel.getText());
        } catch (NumberFormatException e) {
            throw new AssertionError("Unable to retrieve the number of the current block");
        }
    }

    public int getPageNumber() {
        logger.info("Getting the current page number");
        try {
            return Integer.parseInt(pageNumberLabel.getText().split(" ")[0]);
        } catch (Throwable e) {
            throw new AssertionError("Unable to retrieve the current page number");
        }
    }

    public void resetDynamicElements() {
        logger.info("Resetting the dynamic screen elements that are going to be changed");
        blockView.resetWebElement();
        blockLabel.resetWebElement();
        pageNumberLabel.resetWebElement();
    }

}
