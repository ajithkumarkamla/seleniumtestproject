package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    By txtUsername = By.name("username");
    By txtPassword = By.name("password");
    By btnLogin = By.xpath("//button[@type='submit']");
    By dashboard = By.xpath("//a[@class='oxd-main-menu-item active']");
    By userDropdown = By.xpath("//p[@class='oxd-userdropdown-name']");
    By logoutBtn = By.xpath("//a[text()='Logout']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void login(String uname, String pwd) {
        driver.findElement(txtUsername).clear();
        driver.findElement(txtUsername).sendKeys(uname);

        driver.findElement(txtPassword).clear();
        driver.findElement(txtPassword).sendKeys(pwd);

        driver.findElement(btnLogin).click();
    }

    public boolean isDashboardDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboard));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void logout() {
        driver.findElement(userDropdown).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn)).click();
    }
}
