package utilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    public static ExtentReports extent;
    public static ExtentTest test;

    public static void initReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
        spark.config().setReportName("OrangeHRM Automation Report");

        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    public static ExtentTest createTest(String name) {
        test = extent.createTest(name);
        return test;
    }

    public static void flushReport() {
        extent.flush();
    }
}
