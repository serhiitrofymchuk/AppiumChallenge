package screens;

import elements.LabelWrapper;
import elements.ViewWrapper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.GestureUtil;

public class DragAndDropScreen extends BaseScreen<DragAndDropScreen> {

    private static final Logger logger = LoggerFactory.getLogger(DragAndDropScreen.class);

    private final ViewWrapper dropZoneView = new ViewWrapper(driver, AppiumBy.accessibilityId("dropzone"));
    private final LabelWrapper circleLabel = new LabelWrapper(driver, AppiumBy.accessibilityId("dragMe"));
    private final LabelWrapper successLabel = new LabelWrapper(driver, AppiumBy.accessibilityId("success"));

    public DragAndDropScreen(AppiumDriver driver) {
        super(driver, "Drag & Drop");

        logger.info("The Drag & Drop screen initialized");
    }

    @Override
    public boolean isDisplayed() {
        logger.info("Checking if the Drag & Drop screen is displayed");

        // "Drag me!" label should not be always present on the screen that's why it shouldn't be checked here
        return dropZoneView.isDisplayed() && headerPanel.isDisplayed();
    }

    public DragAndDropScreen dragAndDropCircle() {
        logger.info("Dragging the circle and dropping it on the drop zone");
        GestureUtil.dragAndDrop(driver, circleLabel, dropZoneView);
        return this;
    }

    public boolean isSuccessLabelDisplayed() {
        logger.info("Checking if the success label is displayed");
        return successLabel.isPresent() && successLabel.isDisplayed();
    }

    public boolean isCircleDisplayed() {
        logger.info("Checking if the circle label is displayed");
        return circleLabel.isPresent() && circleLabel.isDisplayed();
    }

    public String getSuccessLabelText() {
        logger.info("Getting the success label's text");
        return successLabel.isPresent() ? successLabel.getText() : "";
    }
}
