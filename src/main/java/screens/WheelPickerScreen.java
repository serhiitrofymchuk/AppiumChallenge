package screens;

import elements.DropdownWrapper;
import elements.LabelWrapper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WheelPickerScreen extends BaseScreen<WheelPickerScreen> {

    private static final Logger logger = LoggerFactory.getLogger(WheelPickerScreen.class);

    private static final String LABEL_PREFIX = "Current Color:";

    private final LabelWrapper label;
    private final DropdownWrapper dropdown;

    public WheelPickerScreen(AppiumDriver driver) {
        super(driver, "Wheel Picker Demo");

        label = new LabelWrapper(driver, AppiumBy.xpath("//android.widget.TextView[contains(@text, '" + LABEL_PREFIX + "')]"));

        dropdown = new DropdownWrapper(driver,
                AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/text1']/ancestor::android.widget.Spinner"),
                AppiumBy.id("android:id/text1"),
                AppiumBy.xpath("//android.widget.ListView/android.widget.CheckedTextView"));

        logger.info("The Wheel Picker screen initialized");
    }

    @Override
    public boolean isDisplayed() {
        logger.info("Checking if the Wheel Picker screen is displayed");
        return label.isDisplayed() && dropdown.isDisplayed() && headerPanel.isDisplayed();
    }

    public String getSelectedColor() {
        logger.info("Getting the selected color");
        return label.getText().trim().substring(LABEL_PREFIX.length()).trim();
    }

    public String getSelectedDropdownOption() {
        logger.info("Getting the selected dropdown option");
        return dropdown.getSelectedOption();
    }

    public WheelPickerScreen selectColor(String color) {
        logger.info("Selecting the '{}' color using the dropdown", color);
        dropdown.openDropdown()
                .selectOption(color);
        return this;
    }

}
