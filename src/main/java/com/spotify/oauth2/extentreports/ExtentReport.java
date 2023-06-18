package com.spotify.oauth2.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.spotify.oauth2.constants.FrameworkConstants;
import com.spotify.oauth2.enums.Author;
import com.spotify.oauth2.enums.Category;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.awt.*;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentReport {

	private static ExtentReports extent;

	public static void initReports() {

		if (Objects.isNull(extent))

			extent = new ExtentReports();

		ExtentSparkReporter spark = ExtentHelper.getExtentSparkReporterInstance();

		ExtentHelper.setDocumentTitleForExtentReport(spark, FrameworkConstants.FRAMEWORK_EXTENTREPORT_DOCUMENT_TITLE);
		ExtentHelper.setReportNameForExtentReport(spark, FrameworkConstants.FRAMEWORK_EXTENTREPORT_NAME);
		ExtentHelper.setViewingOrder(spark, FrameworkConstants.FRAMEWORK_EXTENTREPORT_VIEWING_ORDER);
		ExtentHelper.getCustomizedExtentReport(spark);
		ExtentHelper.attachReporter(extent, spark);

	}

	@SneakyThrows
	public static void tearDownReports() {

		if (Objects.nonNull(extent)) {
			extent.flush();
		}

		Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportPath()).toURI());

	}

	public static void createTest(String testCaseName, String description) {
		
		ExtentTest test = extent.createTest(testCaseName, description);

		ExtentManager.setExtentTest(test);

	}
	
	public static void assignAuthor(Author author) {

		ExtentManager.getExtentTest().assignAuthor(author.toString());

	}
	
	
	public static void assignCategory(Category[] category) {

		for (Category temp_category : category) {

			ExtentManager.getExtentTest().assignCategory(temp_category.toString());
		}
	}
	
	public static void setStartTime(long time) {

		ExtentManager.getExtentTest().getModel().setStartTime(getTime(time));

	}
	
	public static void setEndTime(long time) {

		ExtentManager.getExtentTest().getModel().setEndTime(getTime(time));
	}
	
	/**
	 * Returns a Date object representing this Calendar's time value.<br>
	 * 
	 * @param millis - time in long format
	 * @return java.util.Date
	 */

	private static Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
	
}
