package screens;

import elements.LabelWrapper;
import elements.ViewWrapper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.GestureUtil;

public class SamplesListScreen extends BaseScreen<SamplesListScreen> {

    private static final Logger logger = LoggerFactory.getLogger(SamplesListScreen.class);

    private static final String NATIVE_VIEW_SAMPLE_ID = "chainedView";
    private static final String SLIDER_SAMPLE_ID = "slider1";
    private static final String VERTICAL_SWIPING_SAMPLE_ID = "verticalSwipe";
    private static final String DRAG_AND_DROP_SAMPLE_ID = "dragAndDrop";
    private static final String DOUBLE_TAP_SAMPLE_ID = "doubleTap";
    private static final String LONG_PRESS_SAMPLE_ID = "longPress";
    private static final String PHOTO_VIEW_SAMPLE_ID = "photoView";
    private static final String WEB_VIEW_SAMPLE_ID = "webView";
    private static final String CAROUSEL_SAMPLE_ID = "carousel";
    private static final String WHEEL_PICKER_SAMPLE_ID = "wheelPicker";

    private final ViewWrapper parentView = new ViewWrapper(driver, AppiumBy.accessibilityId("scrollView"));

    public SamplesListScreen(AppiumDriver driver) {
        super(driver, "Samples List");

        logger.info("The Samples List screen initialized");
    }

    @Override
    public boolean isDisplayed() {
        logger.info("Checking if the Samples List screen is displayed");
        return headerPanel.isDisplayed() && parentView.isDisplayed();
    }

    public NativeViewScreen tapNativeViewSample() {
        logger.info("Taping the Native View sample");
        tapSample(NATIVE_VIEW_SAMPLE_ID);
        return new NativeViewScreen(driver);
    }

    public SliderScreen tapSliderSample() {
        logger.info("Taping the Slider sample");
        tapSample(SLIDER_SAMPLE_ID);
        return new SliderScreen(driver);
    }

    public VerticalSwipingScreen tapVerticalSwipingSample() {
        logger.info("Taping the Vertical Swiping sample");
        tapSample(VERTICAL_SWIPING_SAMPLE_ID);
        return new VerticalSwipingScreen(driver);
    }

    public DragAndDropScreen tapDragAndDropSample() {
        logger.info("Taping the Drag & Drop sample");
        tapSample(DRAG_AND_DROP_SAMPLE_ID);
        return new DragAndDropScreen(driver);
    }

    public DoubleTapScreen tapDoubleTapSample() {
        logger.info("Taping the Double Tap sample");
        tapSample(DOUBLE_TAP_SAMPLE_ID);
        return new DoubleTapScreen(driver);
    }

    public LongPressScreen tapLongPressSample() {
        logger.info("Taping the Long Press sample");
        tapSample(LONG_PRESS_SAMPLE_ID);
        return new LongPressScreen(driver);
    }

    public PhotoViewScreen tapPhotoViewSample() {
        logger.info("Taping the Photo View sample");
        tapSample(PHOTO_VIEW_SAMPLE_ID);
        return new PhotoViewScreen(driver);
    }

    public WebViewScreen tapWebViewSample() {
        logger.info("Taping the Web View sample");
        tapSample(WEB_VIEW_SAMPLE_ID);
        return new WebViewScreen(driver);
    }

    public CarouselScreen tapCarouselSample() {
        logger.info("Taping the Carousel sample");
        tapSample(CAROUSEL_SAMPLE_ID);
        return new CarouselScreen(driver);
    }

    public WheelPickerScreen tapWheelPickerSample() {
        logger.info("Taping the Wheel Picker sample");
        tapSample(WHEEL_PICKER_SAMPLE_ID);
        return new WheelPickerScreen(driver);
    }

    public String getNativeViewTitleText() {
        logger.info("Getting Native View title text");
        return getSampleTitleText(NATIVE_VIEW_SAMPLE_ID);
    }

    public String getNativeViewSubtitleText() {
        logger.info("Getting Native View subtitle text");
        return getSampleSubtitleText(NATIVE_VIEW_SAMPLE_ID);
    }

    public String getSliderTitleText() {
        logger.info("Getting Slider title text");
        return getSampleTitleText(SLIDER_SAMPLE_ID);
    }

    public String getSliderSubtitleText() {
        logger.info("Getting Slider subtitle text");
        return getSampleSubtitleText(SLIDER_SAMPLE_ID);
    }

    public String getVerticalSwipingTitleText() {
        logger.info("Getting Vertical Swiping title text");
        return getSampleTitleText(VERTICAL_SWIPING_SAMPLE_ID);
    }

    public String getVerticalSwipingSubtitleText() {
        logger.info("Getting Vertical Swiping subtitle text");
        return getSampleSubtitleText(VERTICAL_SWIPING_SAMPLE_ID);
    }

    public String getDragAndDropTitleText() {
        logger.info("Getting Drag & Drop title text");
        return getSampleTitleText(DRAG_AND_DROP_SAMPLE_ID);
    }

    public String getDragAndDropSubtitleText() {
        logger.info("Getting Drag & Drop subtitle text");
        return getSampleSubtitleText(DRAG_AND_DROP_SAMPLE_ID);
    }

    public String getDoubleTapTitleText() {
        logger.info("Getting Double Tap title text");
        return getSampleTitleText(DOUBLE_TAP_SAMPLE_ID);
    }

    public String getDoubleTapSubtitleText() {
        logger.info("Getting Double Tap subtitle text");
        return getSampleSubtitleText(DOUBLE_TAP_SAMPLE_ID);
    }

    public String getLongPressTitleText() {
        logger.info("Getting Long Press title text");
        return getSampleTitleText(LONG_PRESS_SAMPLE_ID);
    }

    public String getLongPressSubtitleText() {
        logger.info("Getting Long Press subtitle text");
        return getSampleSubtitleText(LONG_PRESS_SAMPLE_ID);
    }

    public String getPhotoViewTitleText() {
        logger.info("Getting Photo View title text");
        return getSampleTitleText(PHOTO_VIEW_SAMPLE_ID);
    }

    public String getPhotoViewSubtitleText() {
        logger.info("Getting Photo View subtitle text");
        return getSampleSubtitleText(PHOTO_VIEW_SAMPLE_ID);
    }

    public String getWebViewTitleText() {
        logger.info("Getting Web View title text");
        return getSampleTitleText(WEB_VIEW_SAMPLE_ID);
    }

    public String getWebViewSubtitleText() {
        logger.info("Getting Web View subtitle text");
        return getSampleSubtitleText(WEB_VIEW_SAMPLE_ID);
    }

    public String getCarouselTitleText() {
        logger.info("Getting Carousel title text");
        return getSampleTitleText(CAROUSEL_SAMPLE_ID);
    }

    public String getCarouselSubtitleText() {
        logger.info("Getting Carousel subtitle text");
        return getSampleSubtitleText(CAROUSEL_SAMPLE_ID);
    }

    public String getWheelPickerTitleText() {
        logger.info("Getting Wheel Picker title text");
        return getSampleTitleText(WHEEL_PICKER_SAMPLE_ID);
    }

    public String getWheelPickerSubtitleText() {
        logger.info("Getting Wheel Picker subtitle text");
        return getSampleSubtitleText(WHEEL_PICKER_SAMPLE_ID);
    }

    private void tapSample(String viewId) {
        ViewWrapper sampleView = getSampleView(viewId);
        if (GestureUtil.swipeUpScreenToElement(driver, sampleView)) {
            sampleView.tap();
        } else {
            throw new AssertionError("The sample was not found:\n" + sampleView);
        }
    }

    private ViewWrapper getSampleView(String viewId) {
        return new ViewWrapper(driver, getViewLocator(viewId), parentView);
    }

    private String getSampleTitleText(String viewId) {
        LabelWrapper sampleTitleLabel = new LabelWrapper(driver, getTitleLabelLocator(viewId), parentView);
        if (GestureUtil.swipeUpScreenToElement(driver, sampleTitleLabel)) {
            return sampleTitleLabel.getText();
        } else {
            throw new AssertionError("The sample title label was not found:\n" + sampleTitleLabel);
        }
    }

    private String getSampleSubtitleText(String viewId) {
        LabelWrapper sampleSubtitleLabel = new LabelWrapper(driver, getSubtitleLabelLocator(viewId), parentView);
        if (GestureUtil.swipeUpScreenToElement(driver, sampleSubtitleLabel)) {
            return sampleSubtitleLabel.getText();
        } else {
            throw new AssertionError("The sample subtitle label was not found:\n" + sampleSubtitleLabel);
        }
    }

    private By getViewLocator(String viewId) {
        return AppiumBy.xpath("//android.view.View[@content-desc='" + viewId + "']");
    }

    private By getTitleLabelLocator(String viewId) {
        return AppiumBy.xpath("//android.widget.TextView[@content-desc='" + viewId + "']");
    }

    private By getSubtitleLabelLocator(String viewId) {
        return AppiumBy.xpath("//android.widget.TextView[@content-desc='" + viewId + "']/following-sibling::android.widget.TextView");
    }

}
