package com.Sauce.Reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

	public static ExtentReports extent;
	public static ExtentTest test;

	public static ExtentReports getReporter() {
		if (extent == null) {
			ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Project", "Sauce Labs");
			extent.setSystemInfo("QA", "Ashok");
		}
		return extent;
	}

	public static void flushReport() {
		if (extent != null) {
			extent.flush();
		}
	}
}
