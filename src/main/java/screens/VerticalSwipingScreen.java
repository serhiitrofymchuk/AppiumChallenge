package screens;

import elements.LabelWrapper;
import elements.ViewWrapper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.GestureUtil;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class VerticalSwipingScreen extends BaseScreen<VerticalSwipingScreen> {

    private static final Logger logger = LoggerFactory.getLogger(VerticalSwipingScreen.class);

    private final ViewWrapper parentView =
            new ViewWrapper(driver, AppiumBy.xpath("//android.widget.ScrollView[@content-desc='listview']/android.view.View"));

    public VerticalSwipingScreen(AppiumDriver driver) {
        super(driver, "Vertical swiping");

        logger.info("The Vertical Swiping screen initialized");
    }

    @Override
    public boolean isDisplayed() {
        logger.info("Checking if the Vertical Swiping screen is displayed");
        return parentView.isDisplayed() && headerPanel.isDisplayed();
    }

    public LinkedHashSet<String> swipeUpAndCollectDisplayedItems() {
        logger.info("Swiping up the screen until the bottom is reached and collecting displayed items");
        LinkedHashSet<String> items = new LinkedHashSet<>();
        int swipeAttempts = 0;
        do {
            items.addAll(getCurrentItems());
        } while (GestureUtil.swipeScreenVertically(driver, true) && ++swipeAttempts < 10);
        return items;
    }

    private LinkedHashSet<String> getCurrentItems() {
        logger.info("Getting items that are currently displayed on the screen");

        // Getting the present child WebElements of the parent View
        List<WebElement> webElements = parentView.getChildWebElements(AppiumBy.xpath("//android.view.View"));

        // Converting to the Views
        List<ViewWrapper> views = convertToViews(webElements);

        // Getting the items (text values)
        return getItems(views);
    }

    private List<ViewWrapper> convertToViews(List<WebElement> webElements) {
        logger.debug("Converting the WebElements to the Views");

        return IntStream.range(1, webElements.size() + 1)
                .mapToObj(index -> new ViewWrapper(driver, AppiumBy.xpath("//android.view.View[" + index + "]"), parentView))
                .collect(Collectors.toList());
    }

    private LinkedHashSet<String> getItems(List<ViewWrapper> views) {
        logger.debug("Getting the items (text values)");

        return views.stream()
                // Getting child Labels of the Views
                .map(itemView -> new LabelWrapper(driver, AppiumBy.xpath("//android.widget.TextView"), itemView))
                // Filter the Labels that cannot be found because their Views are not completely displayed on the screen
                .filter(LabelWrapper::isPresent)
                // Getting text values of the Labels
                .map(LabelWrapper::getText)
                // Collect as a LinkedHashSet to avoid possible mistakes of collecting one View twice and to maintain the insertion order
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
