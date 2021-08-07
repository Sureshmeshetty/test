package common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	private static ExtentReports extent;
    public static ExtentTest parentTest;
    public static ExtentTest childTest;
    public ExtentReports extentReports;
    public static String currentDir = System.getProperty("user.dir");
    public static String reportDir = "/target/ExtentReports/";
    public static String fileName = "ExtentReport";
    
    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance(currentDir + reportDir + fileName + ".html");
        }
        return extent;
    }
    
    public static ExtentReports createInstance(String fileName) {
        ExtentSparkReporter sparkReport = new ExtentSparkReporter(fileName);
        

        sparkReport.config().setTheme(Theme.DARK); // theme - standard, dark
        sparkReport.config().setEncoding("UTF-8"); //// encoding, default = UTF-8
        sparkReport.config().setTimelineEnabled(true);
        sparkReport.config().setProtocol(Protocol.HTTPS); // protocol (http, https)
        sparkReport.config().setDocumentTitle("Framewoork Demo"); // report title
        sparkReport.config().setReportName("Sharvya's Report");
        extent = new ExtentReports();
        extent.attachReporter(sparkReport);
        return extent;
    }
}
