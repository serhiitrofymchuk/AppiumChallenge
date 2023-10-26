package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import screens.VerticalSwipingScreen;
import config.TestDataLoader;

import java.util.LinkedHashSet;

public class VerticalSwipingSteps {

    private static final Logger logger = LoggerFactory.getLogger(VerticalSwipingSteps.class);

    private final VerticalSwipingScreen verticalSwipingScreen = new VerticalSwipingScreen(Hooks.getDriver());
    private final LinkedHashSet<String> expectedItems = TestDataLoader.TEST_DATA.getVerticalSwipingData();

    private LinkedHashSet<String> seenItems;

    @When("the user taps the Back button on the Vertical Swiping screen")
    public void userTapsBackButtonOnVerticalSwipingScreen() {
        logger.info("The user is taping the Back button");
        verticalSwipingScreen.tapBackButton();
    }

    @When("the user swipes up the screen till he reaches the bottom")
    public void userSwipesUpScreenTillHeReachesBottom() {
        logger.info("The user is swiping up the screen till he reaches the bottom");
        seenItems = verticalSwipingScreen.swipeUpAndCollectDisplayedItems();
    }

    @Then("the user should have seen all of the screen's items")
    public void userShouldHaveSeenAllOfScreenItems() {
        logger.info("Validation that the user has seen all of the expected items");

        Assert.assertEquals(seenItems, expectedItems, "The items on the screen differ from the expected");
    }

    @Then("the user should see the Vertical Swiping screen")
    public void userShouldSeeVerticalSwipingScreen() {
        logger.info("Validation that the user is on the Vertical Swiping screen");

        Assert.assertTrue(verticalSwipingScreen.isDisplayed(), "The Vertical Swiping screen is not displayed");
    }
}
