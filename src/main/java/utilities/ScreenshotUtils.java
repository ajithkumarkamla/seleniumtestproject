package utilities;

import java.io.File;
import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtils {

    public static String takeScreenshot(WebDriver driver, String name) {
        String path = System.getProperty("user.dir") + "/screenshots/" + name + ".png";
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
}
