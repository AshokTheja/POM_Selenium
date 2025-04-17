package com.Sauce.TestUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class RetryListener implements IAnnotationTransformer {

	@SuppressWarnings("rawtypes")
	public void transform(ITestAnnotation annotation, Class testclass, Constructor testConstructor, Method testMethod) {

		System.out.println("Setting retry analyzer for method: " + testMethod.getName());

		annotation.setRetryAnalyzer(RetryAnalyzer.class);
	}

}
