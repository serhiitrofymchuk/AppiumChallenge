package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import screens.NativeViewScreen;
import config.TestDataLoader;

import java.util.Map;

public class NativeViewSteps {

    private static final Logger logger = LoggerFactory.getLogger(NativeViewSteps.class);

    private final NativeViewScreen nativeViewScreen = new NativeViewScreen(Hooks.getDriver());
    private final Map<String, String> expectedViews = TestDataLoader.TEST_DATA.getNativeViewData();

    @When("the user taps the Back button on the Native View screen")
    public void userTapsBackButtonOnNativeViewScreen() {
        logger.info("The user is taping the Back button");
        nativeViewScreen.tapBackButton();
    }

    @Then("the user should see the Native View screen")
    public void userShouldSeeNativeViewScreen() {
        logger.info("Validation that the user is on the Native View screen");

        Assert.assertTrue(nativeViewScreen.isDisplayed(), "The Native View screen is not displayed");
    }

    @Then("the user should see the expected views")
    public void userShouldSeeExpectedViews() {
        logger.info("Validation that the user sees the expected views");

        Assert.assertEquals(nativeViewScreen.getOneViewText(), expectedViews.get("viewOneText"),
                "The View One's text differs from expected");
        Assert.assertEquals(nativeViewScreen.getTwoViewText(), expectedViews.get("viewTwoText"),
                "The View Two's text differs from expected");
        Assert.assertEquals(nativeViewScreen.getThreeViewText(), expectedViews.get("viewThreeText"),
                "The View Three's text differs from expected");
    }
}
