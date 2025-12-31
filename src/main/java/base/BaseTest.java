package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ConfigReader;
import utilities.ExtentManager;
import utilities.ScreenshotUtils;

public class BaseTest {

    public static WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        ExtentManager.initReport();
    }

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get(ConfigReader.get("url")); // URL from config.properties
    }

    @AfterMethod
    public void checkResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Create a unique name for the screenshot using the method name and the first parameter
            String testName = result.getMethod().getMethodName();
            String username = (result.getParameters().length > 0) ? "_" + result.getParameters()[0].toString() : "";
            String screenshotName = testName + username;

            // Take screenshot and get absolute path
            String absolutePath = ScreenshotUtils.takeScreenshot(driver, screenshotName);
            
            // Create relative path for the report
            String relativePath = "../screenshots/" + screenshotName + ".png";

            // Add screenshot to the report
            ExtentManager.getTest().addScreenCaptureFromPath(relativePath);
        }
    }

    @AfterClass
    public void tearDown() {
        if(driver != null) {
            driver.quit(); // Always quit browser to free resources
        }
    }

    @AfterSuite
    public void afterSuite() {
        ExtentManager.flushReport();
    }
}
