package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MathUtil {

    private static final Logger logger = LoggerFactory.getLogger(MathUtil.class);

    public static int getPercentValue(int totalValue, int percent) {
        logger.info("Getting the {}% value from the following total value: {}", percent, totalValue);
        int result = (int) Math.round(totalValue / 100.0 * percent);
        logger.info("The result: {}", result);
        return result;
    }
}
