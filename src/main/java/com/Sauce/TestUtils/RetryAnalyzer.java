package com.Sauce.TestUtils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	private int retryCount = 0;

	@Override
	public boolean retry(ITestResult result) {

		if (retryCount < 2) {

			System.out.println("Retrying " + result.getName() + " again. Retry Count: " + (retryCount + 1));
			retryCount++;
			return true;
		}

		return false;
	}

}
