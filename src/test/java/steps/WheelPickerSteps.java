package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import screens.WheelPickerScreen;

public class WheelPickerSteps {

    private static final Logger logger = LoggerFactory.getLogger(WheelPickerSteps.class);

    private final WheelPickerScreen wheelPickerScreen = new WheelPickerScreen(Hooks.getDriver());

    @When("the user taps the Back button on the Wheel Picker screen")
    public void userTapsBackButtonOnWheelPickerScreen() {
        logger.info("The user is taping the Back button");
        wheelPickerScreen.tapBackButton();
    }

    @When("the user selects {string} from the dropdown")
    public void userSelectsColorFromDropdown(String color) {
        logger.info("The user is selecting {} from the dropdown", color);
        wheelPickerScreen.selectColor(color);
    }

    @Then("the user should see the Wheel Picker screen")
    public void userShouldSeeWheelPickerScreen() {
        logger.info("Validation that the user is on the Wheel Picker screen");

        Assert.assertTrue(wheelPickerScreen.isDisplayed(), "The Wheel Picker screen is not displayed");
    }

    @Then("the dropdown should have {string} selected")
    public void dropdownShouldHaveSelected(String color) {
        logger.debug("Validation that the dropdown has '{}' selected", color);
        String selectedDropdownOption = wheelPickerScreen.getSelectedDropdownOption();

        Assert.assertEquals(selectedDropdownOption, color, "The dropdown does not have the expected option selected");
    }

    @Then("the current color should be {string}")
    public void currentColorShouldBe(String expectedColor) {
        logger.info("Validation that the current color is {}", expectedColor);
        String actualColor = wheelPickerScreen.getSelectedColor();

        Assert.assertEquals(actualColor, expectedColor, "The selected color differs from the expected");
    }

}
