package common;

import org.testng.IClassListener;
import org.testng.ISuiteListener;
import org.testng.ITestClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class EventListeners extends ExtentManager implements ITestListener,IClassListener,ISuiteListener {


    public synchronized void onStart(ITestContext context) {
        extentReports = ExtentManager.getInstance();
    }

    public synchronized void onFinish(ITestContext context) {
        extentReports.flush();
    }

    public synchronized void AssertFailAndContinue(boolean result, String description) {
        try {
            if (result) {
                childTest.log(Status.PASS, description);
            } else {
                childTest.log(Status.FAIL, description, MediaEntityBuilder.createScreenCaptureFromPath(System.getProperty("user.dir") + "/src/main/resources/17.png").build());
                ITestResult result1 = Reporter.getCurrentTestResult();
                result1.setStatus(2);
            }
        } catch (Exception e) {
        }
    }

    public synchronized void onTestStart(ITestResult result) {
        childTest = parentTest.createNode(result.getName());
        childTest.log(Status.INFO, "Test Started");
    }

    public synchronized void onTestSuccess(ITestResult result) {
    	SafeActions.takeScreenshot(Status.PASS,result.getName());
        childTest.log(Status.PASS, result.getName() + " Passed");
        childTest.log(Status.INFO, result.getName() + " Ended");
    }

    public synchronized void onTestFailure(ITestResult result) {
    	SafeActions.takeScreenshot(Status.FAIL,result.getName());
        childTest.log(Status.FAIL, result.getName() + " Failed");
        childTest.log(Status.INFO, result.getName() + " Ended");
    }

    public synchronized void onTestSkipped(ITestResult result) {
        childTest.log(Status.SKIP, result.getName() + " Skipped");
        childTest.log(Status.INFO, result.getName() + " Ended");
    }

    public void onBeforeClass(ITestClass testClass) {
    	parentTest = extentReports.createTest(testClass.getName());
    }
}
