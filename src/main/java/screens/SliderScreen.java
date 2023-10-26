package screens;

import elements.SliderWrapper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtil;

public class SliderScreen extends BaseScreen<SliderScreen> {

    private static final Logger logger = LoggerFactory.getLogger(SliderScreen.class);

    private final SliderWrapper slider;

    public SliderScreen(AppiumDriver driver) {
        super(driver, "Slider");
        slider = new SliderWrapper(driver, AppiumBy.accessibilityId("slider"),
                AppiumBy.xpath("//android.widget.SeekBar[@content-desc='slider']/preceding-sibling::android.widget.TextView"));
        logger.info("The Slider screen initialized");
    }

    @Override
    public boolean isDisplayed() {
        logger.info("Checking if the Slider screen is displayed");
        return slider.isDisplayed() && headerPanel.isDisplayed();
    }

    public SliderScreen moveSlider(int percent) {
        logger.info("Moving the Slider by {}% to the {}", percent, percent > 0 ? "right" : "left");
        slider.move(percent);
        WaitUtil.getWait(driver)
                .withMessage("The Slider position was not updated after the Slider was moved")
                .until(ExpectedConditions.textToBePresentInElement(slider.getPositionWebElement(), String.valueOf(percent)));
        return this;
    }

    public int getSliderPosition() {
        logger.info("Getting the Slider position");
        return slider.getPosition();
    }
}
