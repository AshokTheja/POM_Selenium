package com.Sauce.Reporting;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Sauce.TestUtils.Test_Utils;
import com.Sauce.Test_Base.TestBase;

public class ExtentTestListener extends TestBase implements ITestListener {

	public void onTestStart(ITestResult result) {
		

		ExtentReportManager.test = ExtentReportManager.getReporter().createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {

		ExtentReportManager.test.pass("Test Passed");

	}

	public void onTestFailure(ITestResult result) {
		String screenshotPath = Test_Utils.captureScreenshot(driver, result.getMethod().getMethodName());
		ExtentReportManager.test.fail("Test Failed: " + result.getThrowable());
		ExtentReportManager.test.addScreenCaptureFromPath(screenshotPath);
	}

	public void onTestSkipped(ITestResult result) {
		ExtentReportManager.test.skip("Test Skipped");
	}

	public void onFinish(ITestContext context) {

		ExtentReportManager.flushReport();

	}

}
