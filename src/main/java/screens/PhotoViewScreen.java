package screens;

import elements.ImageWrapper;
import elements.LabelWrapper;
import elements.ViewWrapper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.GestureUtil;

public class PhotoViewScreen extends BaseScreen<PhotoViewScreen> {

    private static final Logger logger = LoggerFactory.getLogger(PhotoViewScreen.class);

    private final LabelWrapper label = new LabelWrapper(driver, AppiumBy.xpath("//*[@content-desc='photo']/android.widget.TextView"));
    private final ImageWrapper photo = new ImageWrapper(driver, AppiumBy.xpath("//*[@content-desc='photo']/android.widget.ImageView"));

    public PhotoViewScreen(AppiumDriver driver) {
        super(driver, "Photos - Pinch & Zoom");

        logger.info("The Photo View screen initialized");
    }

    @Override
    public boolean isDisplayed() {
        logger.info("Checking if the Photo View screen is displayed");
        return label.isDisplayed() && photo.isDisplayed() && headerPanel.isDisplayed();
    }

    public Dimension getPhotoSize() {
        logger.info("Getting the size of the photo");
        return photo.getSize();
    }

    public PhotoViewScreen pinchAndZoomPhoto() {
        logger.info("Performing pinch & zoom on the photo");
        GestureUtil.pinchAndZoom(driver, photo);
        return this;
    }
}
