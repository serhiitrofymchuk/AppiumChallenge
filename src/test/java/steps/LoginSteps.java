package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import screens.*;
import screens.components.MessagePopup;
import config.TestDataLoader;

public class LoginSteps {

    private static final Logger logger = LoggerFactory.getLogger(LoginSteps.class);

    private final LoginScreen loginScreen = new LoginScreen(Hooks.getDriver());
    private final String validUsername = TestDataLoader.TEST_DATA.getValidUsername();
    private final String validPassword = TestDataLoader.TEST_DATA.getValidPassword();

    private MessagePopup messagePopup;

    @Given("the user is on the Login screen")
    public void userIsOnLoginScreen() {
        logger.info("Validation that the user is on the Login screen");

        Assert.assertTrue(loginScreen.isDisplayed(), "The Login screen is not displayed");
    }

    @Given("the user is on the Samples List screen")
    public void userIsOnSamplesListScreen() {
        logger.info("The user is logging in to the Samples List screen");
        SamplesListScreen samplesListScreen = loginScreen.tapLoginButtonWithValidCredentials();

        Assert.assertTrue(samplesListScreen.isDisplayed(), "The Samples List screen is not displayed");
    }

    @Given("the user is on the Native View screen")
    public void userIsOnNativeViewScreen() {
        logger.info("The user is logging in and navigating to the Native View screen");
        NativeViewScreen nativeViewScreen = loginScreen.tapLoginButtonWithValidCredentials()
                .tapNativeViewSample();

        Assert.assertTrue(nativeViewScreen.isDisplayed(), "The Native View screen is not displayed");
    }

    @Given("the user is on the Slider screen")
    public void userIsOnSliderScreen() {
        logger.info("The user is logging in and navigating to the Slider screen");
        SliderScreen sliderScreen = loginScreen.tapLoginButtonWithValidCredentials()
                .tapSliderSample();

        Assert.assertTrue(sliderScreen.isDisplayed(), "The Slider screen is not displayed");
    }

    @Given("the user is on the Vertical Swiping screen")
    public void userIsOnVerticalSwipingScreen() {
        logger.info("The user is logging in and navigating to the Vertical Swiping screen");
        VerticalSwipingScreen verticalSwipingScreen = loginScreen.tapLoginButtonWithValidCredentials()
                .tapVerticalSwipingSample();

        Assert.assertTrue(verticalSwipingScreen.isDisplayed(), "The Vertical Swiping screen is not displayed");
    }

    @Given("the user is on the Drag & Drop screen")
    public void userIsOnDragAndDropScreen() {
        logger.info("The user is logging in and navigating to the Drag & Drop screen");
        DragAndDropScreen dragAndDropScreen = loginScreen.tapLoginButtonWithValidCredentials()
                .tapDragAndDropSample();

        Assert.assertTrue(dragAndDropScreen.isDisplayed(), "The Drag & Drop screen is not displayed");
    }

    @Given("the user is on the Double Tap screen")
    public void userIsOnDoubleTapScreen() {
        logger.info("The user is logging in and navigating to the Double Tap screen");
        DoubleTapScreen doubleTapScreen = loginScreen.tapLoginButtonWithValidCredentials()
                .tapDoubleTapSample();

        Assert.assertTrue(doubleTapScreen.isDisplayed(), "The Double Tap screen is not displayed");
    }

    @Given("the user is on the Long Press screen")
    public void userIsOnLongPressScreen() {
        logger.info("The user is logging in and navigating to the Long Press screen");
        LongPressScreen longPressScreen = loginScreen.tapLoginButtonWithValidCredentials()
                .tapLongPressSample();

        Assert.assertTrue(longPressScreen.isDisplayed(), "The Long Press screen is not displayed");
    }

    @Given("the user is on the Photo View screen")
    public void userIsOnPhotoViewScreen() {
        logger.info("The user is logging in and navigating to the Photo View screen");
        PhotoViewScreen photoViewScreen = loginScreen.tapLoginButtonWithValidCredentials()
                .tapPhotoViewSample();

        Assert.assertTrue(photoViewScreen.isDisplayed(), "The Photo View screen is not displayed");
    }

    @Given("the user is on the Web View screen")
    public void userIsOnWebViewScreen() {
        logger.info("The user is logging in and navigating to the Web View screen");
        WebViewScreen webViewScreen = loginScreen.tapLoginButtonWithValidCredentials()
                .tapWebViewSample();

        Assert.assertTrue(webViewScreen.isDisplayed(), "The Web View screen is not displayed");
    }

    @Given("the user is on the Carousel screen")
    public void userIsOnCarouselScreen() {
        logger.info("The user is logging in and navigating to the Carousel screen");
        CarouselScreen carouselScreen = loginScreen.tapLoginButtonWithValidCredentials()
                .tapCarouselSample();

        Assert.assertTrue(carouselScreen.isDisplayed(), "The Carousel screen is not displayed");
    }

    @Given("the user is on the Wheel Picker screen")
    public void userIsOnWheelPickerScreen() {
        logger.info("The user is logging in and navigating to the Wheel Picker screen");
        WheelPickerScreen wheelPickerScreen = loginScreen.tapLoginButtonWithValidCredentials()
                .tapWheelPickerSample();

        Assert.assertTrue(wheelPickerScreen.isDisplayed(), "The Wheel Picker screen is not displayed");
    }

    @When("the user enters the valid credentials")
    public void userEntersValidCredentials() {
        logger.info("The user is entering the valid credentials");
        loginScreen.enterUsernameInput(validUsername)
                .enterPasswordInput(validPassword);
    }

    @When("the user enters username {string} and password {string}")
    public void userEntersUsernameAndPassword(String username, String password) {
        logger.info("The user is entering '{}' username and '{}' password", username, password);
        loginScreen.enterUsernameInput(username)
                .enterPasswordInput(password);
    }

    @When("the user taps the Login button")
    public void userTapsLoginButton() {
        logger.info("The user is taping the Login button");
        loginScreen.tapLoginButton();
    }

    @When("the user taps the Login button with invalid credentials")
    public void userTapsLoginButtonWithInvalidCredentials() {
        logger.info("The user is taping the Login button with invalid credentials");
        messagePopup = loginScreen.tapLoginButtonWithInvalidCredentials();
    }

    @When("the user taps the Ok button on the Message Popup of the Login screen")
    public void userTapsOkButtonOnMessagePopup() {
        logger.info("The user is taping the Ok button on the Message Popup");
        Assert.assertNotNull(messagePopup, "The Message Popup is not initialized, precondition steps were not executed");
        messagePopup.tapOkButton()
                .waitToDisappear();
    }

    @Then("the user should see a Message Popup with {string} title and {string} message on the Login screen")
    public void userShouldSeeMessagePopup(String title, String message) {
        logger.info("Validation that the user sees a Message Popup with '{}' title and '{}' message", title, message);

        Assert.assertNotNull(messagePopup, "The Message Popup is not initialized: precondition steps were not executed");
        Assert.assertTrue(messagePopup.isDisplayed(), "The Message Popup is not displayed");
        Assert.assertEquals(messagePopup.getTitleLabelText(), title, "The Message Popup's title text differs from expected");
        Assert.assertEquals(messagePopup.getMessageLabelText(), message, "The Message Popup's message text differs from expected");
    }

    @Then("the Message Popup should disappear from the Login screen")
    public void messagePopupShouldDisappear() {
        logger.info("Validation that the Message Popup disappeared");

        Assert.assertNotNull(messagePopup, "The Message Popup is not initialized: precondition steps were not executed");
        Assert.assertFalse(messagePopup.isPresent(), "The Message Popup did not disappear");
        messagePopup = null;
    }

    @Then("the user should be on the Login screen")
    public void userShouldBeOnTheLoginScreen() {
        logger.info("Validation that the user is on the Login screen");

        Assert.assertTrue(loginScreen.isDisplayed(), "The Login screen is not displayed");
    }
}
