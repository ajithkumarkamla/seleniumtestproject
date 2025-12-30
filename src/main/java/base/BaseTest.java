package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ConfigReader;
import utilities.ExtentManager;

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
        driver.get(ConfigReader.get("url"));
    }

    @AfterClass
    public void tearDown() {
       driver.quit();
    }

    @AfterSuite
    public void afterSuite() {
        ExtentManager.flushReport();
    }
}
