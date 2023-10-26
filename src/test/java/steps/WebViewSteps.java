package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import screens.WebViewScreen;

public class WebViewSteps {

    private static final Logger logger = LoggerFactory.getLogger(WebViewSteps.class);

    private final WebViewScreen webViewScreen = new WebViewScreen(Hooks.getDriver());

    @When("the user taps the Back button on the Web View screen")
    public void userTapsBackButtonOnWebViewScreen() {
        logger.info("The user is taping the Back button");
        webViewScreen.tapBackButton();
    }

    @Then("the user should see the Web View screen")
    public void userShouldSeeWebViewScreen() {
        logger.info("Validation that the user is on the Web View screen");

        Assert.assertTrue(webViewScreen.isDisplayed(), "The Web View screen is not displayed");
    }

    @Then("the user should see the Hacker News web content")
    public void userShouldSeeHackerNewsWebContent() {
        logger.info("Validation that the user is seeing the Hacker News web content");

        Assert.assertTrue(webViewScreen.isHackerNewsWebContentDisplayed(), "The Hacker News web content is not displayed");
    }
}
