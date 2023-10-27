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
4. JDK v11
5. Android SDK (API Level 34, Platform-Tools v34.0.4)
6. Appium v2.1.3
7. Apache Maven v3.8.8

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

The framework configuration files are in the **resouces** folder at _src/test/resources_.

* **appium-config.properties**

It contains standard Appium configuration, such as an Appium server URL and device capabilities.

It also contains the SauceLabs configuration properties like a username and access key that can be commented out in case of the local test run.

Default values for the explicit and implicit wait timeouts are also set there.

All the property values can be overridden with the command line, e.g., `-Dexplicit.wait.timeout=10000` will override the
default explicit wait timeout set in the file.

* **extent.properties**

It is a standard properties file for the ExtentReports configuration.

### Test Execution

#### Local Test Run

To execute the tests **locally**, an Appium server should be up and running on your local machine, as well as an emulator device. The Appium settings in the file or provided via the command line should match your server settings. The emulator device should have an image architecture compatible with the app under test.

#### SauceLabs Test Run

To run the tests on the **SauceLabs** cloud environment, the appropriate Appium settings should be configured. An example of the configuration can be found in the Appium configuration file. The SauceLabs has images with architecture compatible with the app only in its emulator devices that have Android version 8.1. So, make sure to select this type of device in the configuration.

The auto tests run with **Maven**:

    mvn clean test

#### Cucumber Tags

The tests are grouped with the Cucumber **tags**.

**@Navigation** - tests that validate users' ability to navigate from screen to screen.

**@Sample** - the functional tests for the app samples.

Each sample also has its own tag, for example, the **@DragAndDrop** tag for the Drag & Drop screen testing.

If you want to run testing of the Drag & Drop screen only, you need to run this:

    mvn clean test -Dcucumber.filter.tags="@DragAndDrop"

If needed to save time (all the tests run approximately for an hour on the SauceLabs cloud env, and approximately for ten minutes on a local env), and run only the **smoke set** of the tests that validate the main functionalities of the samples, run this:

    mvn clean test -Dcucumber.filter.tags="@Sample"

Thus, the test run will exclude the @Navigation tests, which validate only the navigation between the screens but could be time-consuming.

The tags filtering can be combined: `mvn clean test -Dcucumber.filter.tags="@DragAndDrop and @Sample"`. With this command, only the functional test of the Drag & Drop feature of the screen will be executed.

### Test Data

Expected test data for some tests are stored in the **test-data.json** file of the resources folder at _src/test/resources_.

### Test Reports

As written above, the ExtentReports tool is used for the reporting, and it is configured to place the reports into the ExtentReports folder of the project root folder, it should have subfolders per each test report with timestamps in names. The report has sections grouping tests by the Cucumber tags. It has screenshots for failed tests if such exist.

### P.S.

Thank you for visiting!