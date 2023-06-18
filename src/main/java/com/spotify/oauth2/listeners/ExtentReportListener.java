package com.spotify.oauth2.listeners;

import com.spotify.oauth2.annotations.FrameworkAnnotation;
import com.spotify.oauth2.constants.FrameworkConstants;
import com.spotify.oauth2.extentreports.ExtentLogger;
import com.spotify.oauth2.extentreports.ExtentReport;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;



public class ExtentReportListener implements ITestListener, ISuiteListener {

	private static String getTestDescription(ITestResult result) {

		return result.getMethod().getDescription().isBlank() ? result.getMethod().getMethodName()
				: result.getMethod().getDescription();

	}

	@Override
	public void onStart(ISuite suite) {

		ExtentReport.initReports();
	}

	@Override
	public void onFinish(ISuite suite) {

		ExtentReport.tearDownReports();
	}

	@Override
	public void onTestStart(ITestResult result) {

		ExtentReport.createTest(result.getMethod().getMethodName(), getTestDescription(result));
		ExtentReport.assignAuthor(result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(FrameworkAnnotation.class).author());
		ExtentReport.assignCategory(result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(FrameworkAnnotation.class).category());
		ExtentReport.setStartTime(result.getStartMillis());

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		ExtentLogger.pass(result.getName() + " is " + FrameworkConstants.BOLD_START + "PASSED "
				+ FrameworkConstants.ICON_SMILEY_PASS + FrameworkConstants.BOLD_END);
		ExtentReport.setEndTime(result.getEndMillis());

	}

	@Override
	public void onTestFailure(ITestResult result) {

		ExtentLogger.logStackTraceInfoInExtentReport(String.valueOf(result.getThrowable()));
		ExtentLogger.fail(result.getName() + " is " + FrameworkConstants.BOLD_START + "FAILED     "
				+ FrameworkConstants.ICON_SMILEY_FAIL + FrameworkConstants.BOLD_END);
		ExtentReport.setEndTime(result.getEndMillis());
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		ExtentLogger.skip(result.getName() + " is " + FrameworkConstants.BOLD_START + "SKIPPED "
				+ FrameworkConstants.ICON_SMILEY_SKIP + FrameworkConstants.BOLD_END + " because of"
				+ result.getSkipCausedBy().toString());
		ExtentReport.setEndTime(result.getEndMillis());
	}

}
