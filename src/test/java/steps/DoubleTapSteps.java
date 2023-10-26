package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import screens.DoubleTapScreen;
import screens.components.MessagePopup;

public class DoubleTapSteps {

    private static final Logger logger = LoggerFactory.getLogger(DoubleTapSteps.class);

    private final DoubleTapScreen doubleTapScreen = new DoubleTapScreen(Hooks.getDriver());

    private MessagePopup messagePopup;

    @When("the user taps the Back button on the Double Tap screen")
    public void userTapsBackButtonOnDoubleTapScreen() {
        logger.info("The user is taping the Back button");
        doubleTapScreen.tapBackButton();
    }

    @When("the user taps twice the Double Tap button")
    public void userTapsTwiceDoubleTapButton() {
        logger.info("The user is tapping twice the button");
        messagePopup = doubleTapScreen.doubleTapButton();
    }

    @When("the user taps the Ok button on the Message Popup of the Double Tap screen")
    public void userTapsOkButtonOnMessagePopup() {
        logger.info("The user is taping the Ok button on the Message Popup");
        Assert.assertNotNull(messagePopup, "The Message Popup is not initialized, precondition steps were not executed");
        messagePopup.tapOkButton()
                .waitToDisappear();
    }

    @Then("the user should see a Message Popup with {string} title and {string} message on the Double Tap screen")
    public void userShouldSeeMessagePopup(String title, String message) {
        logger.info("Validation that the user sees a Message Popup with '{}' title and '{}' message", title, message);

        Assert.assertNotNull(messagePopup, "The Message Popup is not initialized, precondition steps were not executed");
        Assert.assertTrue(messagePopup.isDisplayed(), "The Message Popup is not displayed");
        Assert.assertEquals(messagePopup.getTitleLabelText(), title, "The Message Popup's title text differs from expected");
        Assert.assertEquals(messagePopup.getMessageLabelText(), message, "The Message Popup's message text differs from expected");
    }

    @Then("the Message Popup should disappear from the Double Tap screen")
    public void messagePopupShouldDisappear() {
        logger.info("Validation that the Message Popup disappeared");

        Assert.assertNotNull(messagePopup, "The Message Popup is not initialized: precondition steps were not executed");
        Assert.assertFalse(messagePopup.isPresent(), "The Message Popup did not disappear");
        messagePopup = null;
    }

    @Then("the user should see the Double Tap screen")
    public void userShouldSeeDoubleTapScreen() {
        logger.info("Validation that the user is on the Double Tap screen");

        Assert.assertTrue(doubleTapScreen.isDisplayed(), "The Double Tap screen is not displayed");
    }
}
