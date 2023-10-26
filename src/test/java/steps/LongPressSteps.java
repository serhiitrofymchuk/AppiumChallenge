package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import screens.LongPressScreen;
import screens.components.MessagePopup;

public class LongPressSteps {

    private static final Logger logger = LoggerFactory.getLogger(LongPressSteps.class);

    private final LongPressScreen longPressScreen = new LongPressScreen(Hooks.getDriver());

    private MessagePopup messagePopup;

    @When("the user taps the Back button on the Long Press screen")
    public void userTapsBackButtonOnLongPressScreen() {
        logger.info("The user is taping the Back button");
        longPressScreen.tapBackButton();
    }

    @When("the user does a long press on the button")
    public void userDoesLongPressOnButton() {
        logger.info("The user is doing a long press on the button");
        messagePopup = longPressScreen.longPressButton();
    }

    @When("the user taps the Ok button on the Message Popup of the Long Press screen")
    public void userTapsOkButtonOnMessagePopup() {
        logger.info("The user is taping the Ok button on the Message Popup");
        Assert.assertNotNull(messagePopup, "The Message Popup is not initialized, precondition steps were not executed");
        messagePopup.tapOkButton()
                .waitToDisappear();
    }

    @Then("the user should see a Message Popup with {string} title and {string} message on the Long Press screen")
    public void userShouldSeeMessagePopup(String title, String message) {
        logger.info("Validation that the user sees a Message Popup with '{}' title and '{}' message", title, message);

        Assert.assertNotNull(messagePopup, "The Message Popup is not initialized, precondition steps were not executed");
        Assert.assertTrue(messagePopup.isDisplayed(), "The Message Popup is not displayed");
        Assert.assertEquals(messagePopup.getTitleLabelText(), title, "The Message Popup's title text differs from expected");
        Assert.assertEquals(messagePopup.getMessageLabelText(), message, "The Message Popup's message text differs from expected");
    }

    @Then("the Message Popup should disappear from the Long Press screen")
    public void messagePopupShouldDisappear() {
        logger.info("Validation that the Message Popup disappeared");

        Assert.assertNotNull(messagePopup, "The Message Popup is not initialized: precondition steps were not executed");
        Assert.assertFalse(messagePopup.isPresent(), "The Message Popup did not disappear");
        messagePopup = null;
    }

    @Then("the user should see the Long Press screen")
    public void userShouldSeeLongPressScreen() {
        logger.info("Validation that the user is on the Long Press screen");

        Assert.assertTrue(longPressScreen.isDisplayed(), "The Long Press screen is not displayed");
    }
}
