package driver;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import config.ConfigLoader;

import java.io.File;

public class ServerFactory {

    private static final Logger logger = LoggerFactory.getLogger(ServerFactory.class);

    private static AppiumDriverLocalService server;

    public static AppiumDriverLocalService getServer() {
        logger.info("Getting the Appium server");
        if (server == null) {
            server = createServer();
        }
        return server;
    }

    private static AppiumDriverLocalService createServer() {
        logger.info("Creating an Appium server");
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withIPAddress(ConfigLoader.APPIUM_CONFIG.getServerIpAddress())
                .usingPort(ConfigLoader.APPIUM_CONFIG.getServerPort())
                .withArgument(GeneralServerFlag.ALLOW_INSECURE,"chromedriver_autodownload")
                .withArgument(GeneralServerFlag.LOG_LEVEL, "info")
                .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
                .withLogFile(new File("target/appium_server.log"));
        AppiumDriverLocalService server = AppiumDriverLocalService.buildService(builder);
        logger.info("The Appium server created successfully");
        return server;
    }
}
