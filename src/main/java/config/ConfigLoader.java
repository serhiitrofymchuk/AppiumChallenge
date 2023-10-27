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
        return getProperty("appium.serverUrl");
    }

    public String getPlatformName() {
        return getProperty("appium.platformName");
    }

    public String getPlatformVersion() {
        return getProperty("appium.platformVersion");
    }

    public String getDeviceName() {
        return getProperty("appium.deviceName");
    }

    public String getApp() {
        return getProperty("appium.app");
    }

    public String getSauceLabsAppiumVersion() {
        return getProperty("appium.sauceLabs.appiumVersion");
    }

    public String getSauceLabsUsername() {
        return getProperty("appium.sauceLabs.username");
    }

    public String getSauceLabsAccessKey() {
        return getProperty("appium.sauceLabs.accessKey");
    }

    public int getImplicitWaitTimeout() {
        return getIntegerProperty("implicitWait.timeout");
    }

    public int getExplicitWaitTimeout() {
        return getIntegerProperty("explicitWait.timeout");
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
