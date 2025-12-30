package testCases;

import org.testng.Assert;
import org.testng.annotations.*;
import base.BaseTest;
import pageObjects.LoginPage;
import utilities.*;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData", retryAnalyzer = RetryAnalyzer.class)
    public void verifyLogin(String username, String password, String expected) {

        utilities.ExtentManager.createTest("Login Test: " + username);

        LoginPage lp = new LoginPage(driver);
        lp.login(username, password);

        boolean status = lp.isDashboardDisplayed();

        if (expected.equalsIgnoreCase("valid")) {
            if (status) {
                ExtentManager.test.pass("Valid login passed: " + username);
                lp.logout();
            } else {
                ExtentManager.test.fail("Valid login failed: " + username);
                Assert.fail("Login failed for valid user");
            }
        } else {
            if (!status) {
                ExtentManager.test.pass("Invalid login blocked: " + username);
            } else {
                ExtentManager.test.fail("Invalid login passed: " + username);
                lp.logout();
                Assert.fail("Invalid login should not pass");
            }
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() throws Exception {
        return ExcelUtils.getExcelData();
    }
}
