package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import screens.CarouselScreen;
import utils.WaitUtil;

import java.time.Duration;

public class CarouselSteps {

    private static final Logger logger = LoggerFactory.getLogger(CarouselSteps.class);

    private static final Duration AUTO_SWIPE_INTERVAL = Duration.ofMillis(3500);

    private final CarouselScreen carouselScreen = new CarouselScreen(Hooks.getDriver());

    @When("the user taps the Back button on the Carousel screen")
    public void userTapsBackButtonOnCarouselScreen() {
        logger.info("The user is taping the Back button");
        carouselScreen.tapBackButton();
    }

    @When("the user swipes the current block to the left")
    public void userSwipesCurrentBlockToLeft() {
        logger.info("The user is swiping the current block to the left");
        carouselScreen.swipeBlock(true);
    }

    @When("the user swipes the current block to the right")
    public void userSwipesCurrentBlockToRight() {
        logger.info("The user is swiping the current block to the right");
        carouselScreen.swipeBlock(false);
    }

    @Then("the user should see the Carousel screen")
    public void userShouldSeeCarouselScreen() {
        logger.info("Validation that the user is on the Carousel screen");

        Assert.assertTrue(carouselScreen.isDisplayed(), "The Carousel screen is not displayed");
    }

    @Then("the block number {} should be shown")
    public void blockShouldBeShown(int number) {
        logger.info("Validation that the block number {} is shown", number);

        Assert.assertEquals(carouselScreen.getBlockNumber(), number, "The number of the current block differs from the expected");
    }

    @Then("the page number should be {int}")
    public void pageShouldBe(int number) {
        logger.info("Validation that the page number should be {}", number);

        Assert.assertEquals(carouselScreen.getPageNumber(), number, "The current page number differs from the expected");
    }

    @Then("the blocks should swipe automatically")
    public void blocksShouldSwipeAutomatically() {
        logger.info("Validation that the blocks swipe automatically");

        // First block should be present at the beginning
        Assert.assertEquals(carouselScreen.getBlockNumber(), 1, "The number of the current block differs from the expected");
        Assert.assertEquals(carouselScreen.getPageNumber(), 1, "The current page number differs from the expected");
        carouselScreen.resetDynamicElements();

        // Wait for the auto swipe
        WaitUtil.waitFor(AUTO_SWIPE_INTERVAL);

        // Second block should be present at the beginning
        Assert.assertEquals(carouselScreen.getBlockNumber(), 2, "The number of the current block differs from the expected");
        Assert.assertEquals(carouselScreen.getPageNumber(), 2, "The current page number differs from the expected");
        carouselScreen.resetDynamicElements();

        // Wait for the auto swipe
        WaitUtil.waitFor(AUTO_SWIPE_INTERVAL);

        // Third block should be present at the beginning
        Assert.assertEquals(carouselScreen.getBlockNumber(), 3, "The number of the current block differs from the expected");
        Assert.assertEquals(carouselScreen.getPageNumber(), 3, "The current page number differs from the expected");
        carouselScreen.resetDynamicElements();

        // Wait for the auto swipe
        WaitUtil.waitFor(AUTO_SWIPE_INTERVAL);

        // First block should be present at the beginning
        Assert.assertEquals(carouselScreen.getBlockNumber(), 1, "The number of the current block differs from the expected");
        Assert.assertEquals(carouselScreen.getPageNumber(), 1, "The current page number differs from the expected");
        carouselScreen.resetDynamicElements();
    }

}
