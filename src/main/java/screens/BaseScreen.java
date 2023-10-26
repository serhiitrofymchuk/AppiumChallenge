package screens;

import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import screens.components.BaseComponent;
import screens.components.HeaderPanel;

public abstract class BaseScreen<T extends BaseScreen<T>> extends BaseComponent {

    private static final Logger logger = LoggerFactory.getLogger(BaseScreen.class);

    protected final HeaderPanel<T> headerPanel;

    @SuppressWarnings("unchecked")
    protected BaseScreen(AppiumDriver driver, String screenTitle) {
        super(driver);
        this.headerPanel = new HeaderPanel<T>(driver, (T) this, screenTitle);
    }

    public BaseScreen<?> tapBackButton() {
        logger.info("Taping the Back button of the Header Panel");
        return headerPanel.tapBackButton();
    }

}
