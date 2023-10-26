package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Dimension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import screens.PhotoViewScreen;

public class PhotoViewSteps {

    private static final Logger logger = LoggerFactory.getLogger(PhotoViewSteps.class);

    private final PhotoViewScreen photoViewScreen = new PhotoViewScreen(Hooks.getDriver());

    private Dimension sizeBefore;

    @When("the user taps the Back button on the Photo View screen")
    public void userTapsBackButtonOnPhotoViewScreen() {
        logger.info("The user is taping the Back button");
        photoViewScreen.tapBackButton();
    }

    @When("the user zooms in the photo")
    public void userZoomsInPhoto() {
        logger.info("The user is zooming in the photo");

        sizeBefore = photoViewScreen.getPhotoSize();
        logger.info("Size before: {}", sizeBefore);

        photoViewScreen.pinchAndZoomPhoto();
    }

    @Then("the user should see the Photo View screen")
    public void userShouldSeePhotoViewScreen() {
        logger.info("Validation that the user is on the Photo View screen");

        Assert.assertTrue(photoViewScreen.isDisplayed(), "The Photo View screen is not displayed");
    }

    @Then("the photo should be increased in size")
    public void photoShouldBeIncreasedInSize() {
        logger.info("Validation that the photo is increased in size");

        Dimension sizeAfter = photoViewScreen.getPhotoSize();
        logger.info("Size after: {}", sizeAfter);

        Assert.assertTrue(sizeAfter.getWidth() > sizeBefore.getWidth(), "The width of the Photo did not increase");
        Assert.assertTrue(sizeAfter.getHeight() > sizeBefore.getHeight(), "The height of the Photo did not increase");
    }
}
