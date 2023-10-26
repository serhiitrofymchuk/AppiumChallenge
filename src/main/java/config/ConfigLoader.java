package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public enum ConfigLoader {

    APPIUM_CONFIG("src/test/resources/appium-config.properties");

    private final Properties properties = new Properties();

    ConfigLoader(String file) {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new AssertionError("Error loading the Properties file", e);
        }
    }

    public String getServerUrl() {
        return getProperty("appium.server.url");
    }

    public String getServerIpAddress() {
        return getProperty("appium.server.ipAddress");
    }

    public int getServerPort() {
        return getIntegerProperty("appium.server.port");
    }

    public String getPlatformName() {
        return getProperty("appium.platform.name");
    }

    public String getPlatformVersion() {
        return getProperty("appium.platform.version");
    }

    public String getDeviceName() {
        return getProperty("appium.device.name");
    }

    public String getAppLocation() {
        return getProperty("appium.app.location");
    }

    public int getImplicitWaitTimeout() {
        return getIntegerProperty("implicit.wait.timeout");
    }

    public int getExplicitWaitTimeout() {
        return getIntegerProperty("explicit.wait.timeout");
    }

    private String getProperty(String key) {
        String overriddenValue = System.getProperty(key);
        if (overriddenValue != null) {
            return overriddenValue;
        }
        return properties.getProperty(key);
    }

    private int getIntegerProperty(String key) {
        try {
            String value = getProperty(key);
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new AssertionError("Error parsing as an Integer", e);
        }
    }

    private boolean getBooleanProperty(String key) {
        String value = getProperty(key);
        return Boolean.parseBoolean(value);
    }
}
