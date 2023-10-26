package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import screens.DragAndDropScreen;
import config.TestDataLoader;

public class DragAndDropSteps {

    private static final Logger logger = LoggerFactory.getLogger(DragAndDropSteps.class);

    private final DragAndDropScreen dragAndDropScreen = new DragAndDropScreen(Hooks.getDriver());
    private final String expectedSuccessLabelText = TestDataLoader.TEST_DATA.getDragAndDropSuccessLabelText();

    @When("the user taps the Back button on the Drag & Drop screen")
    public void userTapsBackButtonOnDragAndDropScreen() {
        logger.info("The user is taping the Back button");
        dragAndDropScreen.tapBackButton();
    }

    @When("the user drags the circle and drops it on the drop zone")
    public void userDragsCircleAndDropsItOnTarget() {
        logger.info("The user is dragging the circle and dropping it on the drop zone");
        dragAndDropScreen.dragAndDropCircle();
    }

    @When("the user should see the success label and the circle should disappear")
    public void userShouldSeeSuccessLabelAndCircleShouldDisappear() {
        logger.info("Validation that the user is seeing the success label and the circle disappeared");

        Assert.assertTrue(dragAndDropScreen.isSuccessLabelDisplayed(), "The success label is not displayed");
        Assert.assertEquals(dragAndDropScreen.getSuccessLabelText(), expectedSuccessLabelText);
        Assert.assertFalse(dragAndDropScreen.isCircleDisplayed(), "The circle did not disappear");
    }

    @Then("the user should see the Drag & Drop screen")
    public void userShouldSeeDragAndDropScreen() {
        logger.info("Validation that the user is on the Drag & Drop screen");

        Assert.assertTrue(dragAndDropScreen.isDisplayed(), "The Drag & Drop screen is not displayed");
    }
}
