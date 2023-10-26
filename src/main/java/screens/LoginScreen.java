package screens;

import elements.ButtonWrapper;
import elements.InputWrapper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import screens.components.MessagePopup;
import utils.WaitUtil;

public class LoginScreen extends BaseScreen<LoginScreen> {

    private static final Logger logger = LoggerFactory.getLogger(LoginScreen.class);

    private final InputWrapper usernameInput = new InputWrapper(driver, AppiumBy.accessibilityId("username"));
    private final InputWrapper passwordInput = new InputWrapper(driver, AppiumBy.accessibilityId("password"));
    private final ButtonWrapper loginButton = new ButtonWrapper(driver, AppiumBy.xpath("//android.view.View[@content-desc='login']/android.widget.Button"));

    public LoginScreen(AppiumDriver driver) {
        super(driver, "Login");
        logger.info("The Login screen initialized");
    }

    @Override
    public boolean isDisplayed() {
        logger.info("Checking if the Login screen is displayed");
        return usernameInput.isDisplayed()
                && passwordInput.isDisplayed()
                && loginButton.isDisplayed()
                && headerPanel.isDisplayed();
    }

    public LoginScreen enterUsernameInput(String username) {
        logger.info("Entering '{}' into the Username input", username);
        usernameInput.enter(username);
        return this;
    }

    public LoginScreen enterPasswordInput(String password) {
        logger.info("Entering '{}' into the Password input", password);
        passwordInput.enter(password);
        return this;
    }

    public void tapLoginButton() {
        logger.info("Taping the Login button");
        loginButton.tap();
    }

    public SamplesListScreen tapLoginButtonWithValidCredentials() {
        logger.info("Taping the Login button with valid credentials");
        loginButton.tap();
        return new SamplesListScreen(driver);
    }

    public MessagePopup tapLoginButtonWithInvalidCredentials() {
        logger.info("Taping the Login button with invalid credentials");
        loginButton.tap();

        logger.info("Waiting for the Message Popup to appear");
        MessagePopup messagePopup = new MessagePopup(driver);
        WaitUtil.getWait(driver)
                .withMessage("The Message Popup did not appear")
                .until(driver -> messagePopup.isDisplayed());
        return messagePopup;
    }

}
