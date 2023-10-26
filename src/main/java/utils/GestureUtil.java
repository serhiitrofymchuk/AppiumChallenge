package utils;

import elements.BaseWebElementWrapper;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static utils.MathUtil.getPercentValue;

public class GestureUtil {

    private static final Logger logger = LoggerFactory.getLogger(GestureUtil.class);

    // in millis
    private static final long SWIPE_DURATION = 700L;
    private static final long TAP_DURATION = 100L;
    private static final long PAUSE_DURATION = 50L;
    private static final long LONG_PRESS_DURATION = 1000L;

    public static boolean swipeUpScreenToElement(AppiumDriver driver, BaseWebElementWrapper element) {
        return swipeScreenVerticallyToElement(driver, element, true);
    }

    public static boolean swipeScreenVerticallyToElement(AppiumDriver driver, BaseWebElementWrapper element, boolean up) {
        logger.info("Swiping {} the screen vertically till the element shows up: {}", up ? "up" : "down", element);
        int attempts = 0;
        do {
            if (element.isPresent() && element.isDisplayed()) {
                logger.info("The element successfully reached with {} swiping attempts", attempts);
                return true;
            }

            logger.info("Swiping attempt number: {}", attempts + 1);
            boolean screenUpdated = swipeScreenVertically(driver, up);
            attempts++;

            if (!screenUpdated) {
                logger.info("The end of the screen reached but the element didn't show up");
                return false;
            }
        } while (attempts < 10);
        boolean result = element.isPresent() && element.isDisplayed();
        logger.info(result ? "The element successfully reached" : "The element was not reached");
        return result;
    }

    public static boolean swipeScreenVertically(AppiumDriver driver, boolean up) {
        logger.info("Swiping {} the screen", up ? "up" : "down");
        String screenBefore = driver.getPageSource();

        Dimension screenSize = driver.manage().window().getSize();
        // 20% from the top of the screen
        int screenTopY = getPercentValue(screenSize.getHeight(), 20);
        // 80% from the top of the screen
        int screenBottomY = getPercentValue(screenSize.getHeight(), 80);

        int x = screenSize.getWidth() / 2;
        int startY = up ? screenBottomY : screenTopY;
        int endY = up ? screenTopY : screenBottomY;

        swipe(driver, x, startY, x, endY);

        String screenAfter = driver.getPageSource();

        // Returns true if the screen was changed as the swiping result
        return !screenAfter.equals(screenBefore);
    }

    public static void swipeElementHorizontally(AppiumDriver driver, BaseWebElementWrapper element, boolean left) {
        logger.info("Swiping the '{}' element horizontally to the {} edge of the screen", element, left ? "left" : "right");

        // Center of the element
        int startX = element.getLocation().getX() + getPercentValue(element.getSize().getWidth(), 50);
        int y = element.getLocation().getY() + getPercentValue(element.getSize().getHeight(), 50);

        // The screen side edge
        int endX = left ? 0 : driver.manage().window().getSize().getWidth();

        swipe(driver, startX, y, endX, y);
    }

    public static void swipeOverElementHorizontally(AppiumDriver driver, BaseWebElementWrapper element, int percent) {
        logger.info("Swiping over the '{}' element horizontally by {}% to the {}", element, percent, percent > 0 ? "right" : "left");

        // Distance of the horizontal swiping
        int xOffset = getPercentValue(element.getSize().getWidth(), percent);

        int startX = element.getLocation().getX();
        int endX = startX + xOffset;
        int y = element.getLocation().getY();

        swipe(driver, startX, y, endX, y);
    }

    public static void swipe(AppiumDriver driver, int startX, int startY, int endX, int endY) {
        logger.info("Swiping from the start coordinates ({}; {}) to the end coordinates ({}; {})", startX, startY, endX, endY);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 4)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(SWIPE_DURATION), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(sequence));
    }

    public static void dragAndDrop(AppiumDriver driver, BaseWebElementWrapper drag, BaseWebElementWrapper drop) {
        logger.info("Dragging the '{}' element and dropping it on the '{}' element", drag, drop);

        // Center of the Drag element
        int startX = drag.getLocation().getX() + getPercentValue(drag.getSize().getWidth(), 50);
        int startY = drag.getLocation().getY() + getPercentValue(drag.getSize().getHeight(), 50);

        // Center of the Drop element
        int endX = drop.getLocation().getX() + getPercentValue(drop.getSize().getWidth(), 50);
        int endY = drop.getLocation().getY() + getPercentValue(drop.getSize().getHeight(), 50);

        swipe(driver, startX, startY, endX, endY);
    }

    public static void doubleTap(AppiumDriver driver, BaseWebElementWrapper element) {
        logger.info("Double tapping the element: {}", element);

        Point elementLocation = element.getWebElement().getLocation();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence sequence = new Sequence(finger, 8)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), elementLocation))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(TAP_DURATION)))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(PAUSE_DURATION)))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(TAP_DURATION)))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(sequence));
    }

    public static void longPress(AppiumDriver driver, BaseWebElementWrapper element) {
        logger.info("Long pressing the element: {}", element);

        Point elementLocation = element.getWebElement().getLocation();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence sequence = new Sequence(finger, 4)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), elementLocation))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(LONG_PRESS_DURATION)))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(sequence));
    }

    public static void pinchAndZoom(AppiumDriver driver, BaseWebElementWrapper element) {
        logger.info("Pinching and zooming the element: {}", element);

        // 5% of the element's height above the center of the element
        int upFingerStartX = element.getLocation().getX() + getPercentValue(element.getSize().getWidth(), 50);
        int upFingerStartY = element.getLocation().getY() + getPercentValue(element.getSize().getHeight(), 45);

        // Higher than the center on 25% of the element's width
        int upFingerEndX = element.getLocation().getX() + getPercentValue(element.getSize().getWidth(), 50);
        int upFingerEndY = element.getLocation().getY() + getPercentValue(element.getSize().getHeight(), 25);

        // 5% of the element's height below the center of the element
        int downFingerStartX = element.getLocation().getX() + getPercentValue(element.getSize().getWidth(), 50);
        int downFingerStartY = element.getLocation().getY() + getPercentValue(element.getSize().getHeight(), 55);

        // Lower than the center on 25% of the element's width
        int downFingerEndX = element.getLocation().getX() + getPercentValue(element.getSize().getWidth(), 50);
        int downFingerEndY = element.getLocation().getY() + getPercentValue(element.getSize().getHeight(), 75);

        Dimension windowSize = driver.manage().window().getSize();
        logger.info("The screen size: X = {}; Y = {}", windowSize.getWidth(), windowSize.getHeight());
        logger.info("The up finger starts at ({}; {}) and ends at ({}; {})", upFingerStartX, upFingerStartY, upFingerEndX, upFingerEndY);
        logger.info("The down finger starts at ({}; {}) and ends at ({}; {})", downFingerStartX, downFingerStartY, downFingerEndX, downFingerEndY);

        PointerInput upFinger = new PointerInput(PointerInput.Kind.TOUCH, "upFinger");
        PointerInput downFinger = new PointerInput(PointerInput.Kind.TOUCH, "downFinger");

        Sequence upFingerSequence =
                getPinchAndZoomFingerSequence(upFinger, upFingerStartX, upFingerStartY, upFingerEndX, upFingerEndY);

        Sequence downFingerSequence =
                getPinchAndZoomFingerSequence(downFinger, downFingerStartX, downFingerStartY, downFingerEndX, downFingerEndY);

        driver.perform(Arrays.asList(upFingerSequence, downFingerSequence));
    }

    private static Sequence getPinchAndZoomFingerSequence(PointerInput finger, int startX, int startY, int endX, int endY) {
        return new Sequence(finger, 5)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(TAP_DURATION)))
                .addAction(finger.createPointerMove(Duration.ofMillis(SWIPE_DURATION), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    }

}
