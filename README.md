# Appium Challenge

## Mobile UI Test Automation Framework with Appium and BDD (Cucumber)

The goal of this project is to show the ability and skills for building a test automation framework based on Appium and
Cucumber BDD for mobile app testing.

### Application under Test

It is an Android app with a wide set of different UI elements and functionalities found in almost every mobile
application.

One element/functionality that represents a single mobile UI feature is called a **sample** in this app.

The app has three layers of _screens_ (pages): **login** screen , **samples list** screen (contains a list of sample
names that leads to sample screens), and **sample** screens - a screen per sample.

The **apk** file can be found at _src/test/resources_.

#### App Properties

* targetSdkVersion: '22'
* native-code: 'armeabi-v7a' 'x86'

### Prerequisites

1. A macOS, Linux, or Windows operating system
2. NPM v10.2.0
3. Node.js v20.8.0
4. Appium v2.1.3
5. Android SDK (API Level 34, Platform-Tools v34.0.4)
6. JDK v11
7. Appium driver UiAutomator2 v2.29.10
8. Apache Maven v3.8.8

### Tech Stack & Tools

* Java 11
* Appium
* Cucumber
* TestNG
* ExtentReports
* Maven
* SLF4J
* JSON.simple

### Framework Settings

The framework configuration files are in the **resouces** folder as usually at _src/test/resources_.

* **appium-config.properties**

It contains standard Appium configuration, such as an Appium server URL and device capabilities.

The Appium server IP address and port properties are used to start the server programmatically at the beginning of a
test run.

Default values for the explicit and implicit wait timeouts are also set there.

All the property values can be overridden with the command line, e.g., `-Dexplicit.wait.timeout=10000` will override the
default explicit wait timeout set in the file.

* **extent.properties**

It is a standard properties file for the ExtentReports configuration.

### Test Execution

The auto tests run with **Maven**:

    mvn clean test

The Appium server, as written above, starts programmatically, so make sure that the port set in the configuration file
or provided via the command line is free (`:4723` by default, if your Appium server is running with this port, then shut
down it before running the tests).

#### Testing on an Emulator Device

The emulator device should have an Android version and image architecture compatible with the app under test. Obviously, it should match the device capabilities settings configured in the appium-config.properties file or provided via the command line. And it should be up and running, of course :) 

#### Cucumber Tags

The tests are grouped with the Cucumber **tags**.

**@Navigation** - tests that validate users' ability to navigate from screen to screen.

**@Sample** - the functional tests for the app samples.

Each sample also has its own tag, for example, the **@DragAndDrop** tag for the Drag & Drop screen testing.

So, if you want to run testing of the Drag & Drop screen, excluding other screens and excluding testing of the navigation
to this screen, you need to run this:

    mvn clean test -Dcucumber.filter.tags="@DragAndDrop and @Sample"

### Test Data

Expected test data for some tests are stored in the **test-data.json** file of the resources folder at _src/test/resources_.

### Test Reports

As written above, the ExtentReports tool is used for the reporting, and it is configured to place the reports into the ExtentReports folder of the project root folder, it should have subfolders per each test report with timestamps in names. The report has sections grouping tests by the Cucumber tags. It has screenshots for failed tests if such exist.

### P.S.

Thank you for visiting!