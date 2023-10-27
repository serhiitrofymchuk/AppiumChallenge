package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class TimeUtil {

    private static final Logger logger = LoggerFactory.getLogger(TimeUtil.class);

    private static final String DEFAULT_TIMESTAMP_PATTERN = "yyyy.MM.dd-HH:mm:ss";

    public static long durationToSeconds(Duration duration) {
        logger.debug("Converting the duration to seconds. The duration: {}", duration);
        long seconds = duration.toSeconds();
        logger.debug("Result seconds: {}", seconds);
        return seconds;
    }

    public static String getCurrentTimestamp() {
        return getCurrentTimestamp(DEFAULT_TIMESTAMP_PATTERN);
    }

    public static String getCurrentTimestamp(String pattern) {
        logger.info("Getting the current timestamp with the pattern: {}", pattern);
        String timestamp = new SimpleDateFormat(pattern).format(new Date());
        logger.info("The current timestamp: {}", timestamp);
        return timestamp;
    }
}
