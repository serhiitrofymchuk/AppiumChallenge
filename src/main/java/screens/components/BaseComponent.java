package screens.components;

import io.appium.java_client.AppiumDriver;

public abstract class BaseComponent {

    protected AppiumDriver driver;

    protected BaseComponent(AppiumDriver driver) {
        this.driver = driver;
    }

    public abstract boolean isDisplayed();

}
