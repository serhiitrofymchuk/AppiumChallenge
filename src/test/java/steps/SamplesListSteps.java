package steps;

import config.TestDataLoader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import screens.SamplesListScreen;

public class SamplesListSteps {

    private static final Logger logger = LoggerFactory.getLogger(SamplesListSteps.class);

    private final SamplesListScreen samplesListScreen = new SamplesListScreen(Hooks.getDriver());
    private final JSONObject samplesTestData = TestDataLoader.TEST_DATA.getSamplesListData();

    @When("the user taps the Back button on the Samples List screen")
    public void userTapsBackButtonOnSamplesListScreen() {
        logger.info("The user is taping the Back button");
        samplesListScreen.tapBackButton();
    }

    @When("the user taps the Native View sample")
    public void userTapsNativeViewSample() {
        logger.info("The user is taping the Native View sample");
        samplesListScreen.tapNativeViewSample();
    }

    @When("the user taps the Slider sample")
    public void userTapsSliderSample() {
        logger.info("The user is taping the Slider sample");
        samplesListScreen.tapSliderSample();
    }

    @When("the user taps the Vertical Swiping sample")
    public void userTapsVerticalSwipingSample() {
        logger.info("The user is taping the Vertical Swiping sample");
        samplesListScreen.tapVerticalSwipingSample();
    }

    @When("the user taps the Drag & Drop sample")
    public void userTapsDragAndDropSample() {
        logger.info("The user is taping the Drag & Drop sample");
        samplesListScreen.tapDragAndDropSample();
    }

    @When("the user taps the Double Tap sample")
    public void userTapsDoubleTapSample() {
        logger.info("The user is taping the Double Tap sample");
        samplesListScreen.tapDoubleTapSample();
    }

    @When("the user taps the Long Press sample")
    public void userTapsLongPressSample() {
        logger.info("The user is taping the Long Press sample");
        samplesListScreen.tapLongPressSample();
    }

    @When("the user taps the Photo View sample")
    public void userTapsPhotoViewSample() {
        logger.info("The user is taping the Photo View sample");
        samplesListScreen.tapPhotoViewSample();
    }

    @When("the user taps the Web View sample")
    public void userTapsWebViewSample() {
        logger.info("The user is taping the Web View sample");
        samplesListScreen.tapWebViewSample();
    }

    @When("the user taps the Carousel sample")
    public void userTapsCarouselSample() {
        logger.info("The user is taping the Carousel sample");
        samplesListScreen.tapCarouselSample();
    }

    @When("the user taps the Wheel Picker sample")
    public void userTapsWheelPickerSample() {
        logger.info("The user is taping the Wheel Picker sample");
        samplesListScreen.tapWheelPickerSample();
    }

    @Then("the user should be on the Samples List screen")
    public void userShouldBeOnSamplesListScreen() {
        logger.info("Validation that the user is on the Samples List screen");

        Assert.assertTrue(samplesListScreen.isDisplayed(), "The Samples List screen is not displayed");
    }

    @Then("the user should see the Samples List items")
    public void userShouldSeeSamplesListItems() {
        logger.info("Validation that the user sees the samples of the Samples List screen");

        JSONObject sampleTestData = (JSONObject) samplesTestData.get("nativeViewSample");
        Assert.assertEquals(samplesListScreen.getNativeViewTitleText(), (String) sampleTestData.get("title"),
                "The Title label of the Native View sample has incorrect text");
        Assert.assertEquals(samplesListScreen.getNativeViewSubtitleText(), (String) sampleTestData.get("subtitle"),
                "The Subtitle label of the Native View sample has incorrect text");

        sampleTestData = (JSONObject) samplesTestData.get("sliderSample");
        Assert.assertEquals(samplesListScreen.getSliderTitleText(), (String) sampleTestData.get("title"),
                "The Title label of the Slider sample has incorrect text");
        Assert.assertEquals(samplesListScreen.getSliderSubtitleText(), (String) sampleTestData.get("subtitle"),
                "The Subtitle label of the Slider sample has incorrect text");

        sampleTestData = (JSONObject) samplesTestData.get("verticalSwipingSample");
        Assert.assertEquals(samplesListScreen.getVerticalSwipingTitleText(), (String) sampleTestData.get("title"),
                "The Title label of the Vertical Swiping sample has incorrect text");
        Assert.assertEquals(samplesListScreen.getVerticalSwipingSubtitleText(), (String) sampleTestData.get("subtitle"),
                "The Subtitle label of the Vertical Swiping sample has incorrect text");

        sampleTestData = (JSONObject) samplesTestData.get("dragAndDropSample");
        Assert.assertEquals(samplesListScreen.getDragAndDropTitleText(), (String) sampleTestData.get("title"),
                "The Title label of the Drag & Drop sample has incorrect text");
        Assert.assertEquals(samplesListScreen.getDragAndDropSubtitleText(), (String) sampleTestData.get("subtitle"),
                "The Subtitle label of the Drag & Drop sample has incorrect text");

        sampleTestData = (JSONObject) samplesTestData.get("doubleTapSample");
        Assert.assertEquals(samplesListScreen.getDoubleTapTitleText(), (String) sampleTestData.get("title"),
                "The Title label of the Double Tap sample has incorrect text");
        Assert.assertEquals(samplesListScreen.getDoubleTapSubtitleText(), (String) sampleTestData.get("subtitle"),
                "The Subtitle label of the Double Tap sample has incorrect text");

        sampleTestData = (JSONObject) samplesTestData.get("longPressSample");
        Assert.assertEquals(samplesListScreen.getLongPressTitleText(), (String) sampleTestData.get("title"),
                "The Title label of the Long Press sample has incorrect text");
        Assert.assertEquals(samplesListScreen.getLongPressSubtitleText(), (String) sampleTestData.get("subtitle"),
                "The Subtitle label of the Long Press sample has incorrect text");

        sampleTestData = (JSONObject) samplesTestData.get("photoViewSample");
        Assert.assertEquals(samplesListScreen.getPhotoViewTitleText(), (String) sampleTestData.get("title"),
                "The Title label of the Photo View sample has incorrect text");
        Assert.assertEquals(samplesListScreen.getPhotoViewSubtitleText(), (String) sampleTestData.get("subtitle"),
                "The Subtitle label of the Photo View sample has incorrect text");

        sampleTestData = (JSONObject) samplesTestData.get("webViewSample");
        Assert.assertEquals(samplesListScreen.getWebViewTitleText(), (String) sampleTestData.get("title"),
                "The Title label of the Web View sample has incorrect text");
        Assert.assertEquals(samplesListScreen.getWebViewSubtitleText(), (String) sampleTestData.get("subtitle"),
                "The Subtitle label of the Web View sample has incorrect text");

        sampleTestData = (JSONObject) samplesTestData.get("carouselSample");
        Assert.assertEquals(samplesListScreen.getCarouselTitleText(), (String) sampleTestData.get("title"),
                "The Title label of the Carousel sample has incorrect text");
        Assert.assertEquals(samplesListScreen.getCarouselSubtitleText(), (String) sampleTestData.get("subtitle"),
                "The Subtitle label of the Carousel sample has incorrect text");

        sampleTestData = (JSONObject) samplesTestData.get("wheelPickerSample");
        Assert.assertEquals(samplesListScreen.getWheelPickerTitleText(), (String) sampleTestData.get("title"),
                "The Title label of the Wheel Picker sample has incorrect text");
        Assert.assertEquals(samplesListScreen.getWheelPickerSubtitleText(), (String) sampleTestData.get("subtitle"),
                "The Subtitle label of the Wheel Picker sample has incorrect text");
    }
}
