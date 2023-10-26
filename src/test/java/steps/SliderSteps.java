package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import screens.SliderScreen;

public class SliderSteps {


    private static final Logger logger = LoggerFactory.getLogger(SliderSteps.class);

    private final SliderScreen sliderScreen = new SliderScreen(Hooks.getDriver());

    @When("the user taps the Back button on the Slider screen")
    public void userTapsBackButtonOnSliderScreen() {
        logger.info("The user is taping the Back button");
        sliderScreen.tapBackButton();
    }

    @When("the user moves the slider right by {int}%")
    public void userMovesSliderRightBy(int percent) {
        logger.info("The user is moving the slider by {}% to the right", percent);
        sliderScreen.moveSlider(percent);
    }

    @Then("the user should see the Slider screen")
    public void userShouldSeeSliderScreen() {
        logger.info("Validation that the user is on the Slider screen");

        Assert.assertTrue(sliderScreen.isDisplayed(), "The Slider screen is not displayed");
    }

    @Then("the Slider should be shown righter on {int}%")
    public void sliderShouldBeShownRighterOn(int percent) {
        logger.info("Validation that the Slider is shown righter on {}%", percent);

        Assert.assertEquals(sliderScreen.getSliderPosition(), percent, "The Slider position differs from expected");
    }

}
