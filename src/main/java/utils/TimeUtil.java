package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class TimeUtil {

    private static final Logger logger = LoggerFactory.getLogger(TimeUtil.class);

    public static long durationToSeconds(Duration duration) {
        logger.debug("Converting the duration to seconds. The duration: {}", duration);
        long seconds = duration.toSeconds();
        logger.debug("Result seconds: {}", seconds);
        return seconds;
    }

}
