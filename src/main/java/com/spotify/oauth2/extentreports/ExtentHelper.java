package com.spotify.oauth2.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.spotify.oauth2.constants.FrameworkConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentHelper {
	

	static ExtentSparkReporter getExtentSparkReporterInstance() {

		return new ExtentSparkReporter(FrameworkConstants.getExtentReportPath());

	}
	
	
	/**
	 * Returns customized JS script for beautification of Extent Report.
	 * 
	 * @return customized JS script which is injected for beautification in
	 *         {@link com.aventstack.extentreports.reporter.ExtentSparkReporter}.
	 */

	private static String customizedJSForSparkReport() {

		return "var els = document.getElementsByClassName(\"m-t-10 m-l-5\");\r\n" + "\r\n"
				+ "        for (var i=0; i<els.length; i++) {\r\n" + "            els[i].style.fontStyle=\"italic\"\r\n"
				+ "            els[i].style.fontSize=\"14px\"\r\n" + "\r\n" + "}\r\n" + "\r\n"
				+ "var end_time = document.querySelectorAll('span.badge-danger:not(.log)');\r\n" + "\r\n" + "\r\n"
				+ "for (var i=0; i<end_time.length; i++) {\r\n"
				+ "            end_time[i].style.backgroundColor=\"#6c757d\"\r\n" + "             \r\n" + " }\r\n"
				+ " \r\n" + " var end_time = document.querySelectorAll('span.badge-success:not(.log)');\r\n" + "\r\n"
				+ "\r\n" + "for (var i=0; i<end_time.length; i++) {\r\n"
				+ "            end_time[i].style.backgroundColor=\"#6c757d\"\r\n" + "             \r\n" + " }\r\n"
				+ "\r\n" + "var methodName = document.querySelectorAll('h5.test-status');\r\n" + "\r\n"
				+ "for (var i=0; i<methodName.length; i++) {\r\n"
				+ "            methodName[i].style.fontSize=\"18px\"\r\n"
				+ "            methodName[i].style.fontFamily=\"Arial Black\"\r\n" + "\r\n" + "             \r\n"
				+ " }\r\n" + "\r\n"
				+ "var start_time = document.querySelectorAll('span.badge-default:not(.badge-pill):not(.uri-anchor)');\r\n"
				+ "\r\n" + "\r\n" + "for (var i=0; i<start_time.length; i++) {\r\n"
				+ "            start_time[i].style.backgroundColor=\"#343a40\"\r\n" + "             \r\n" + " }\r\n"
				+ "\r\n" + "\r\n" + "document.getElementsByClassName('logo')[0].style.backgroundImage = \"url('')\"\r\n"
				+ "\r\n" + "document.getElementsByClassName('nav-logo')[0].style.backgroundImage = \""
				+ FrameworkConstants.FRAMEWORK_EXTENT_LOGO_PATH + "\"\r\n" + "\r\n"
				+ "document.getElementsByClassName('nav-logo')[0].style.width=\"70px\"\r\n" + "\r\n"
				+ "document.getElementsByClassName('nav-logo')[0].style.height=\"70px\"";

	}
	
	/**
	 * Sets custom JS script in
	 * {@link com.aventstack.extentreports.reporter.ExtentSparkReporter} instance
	 * attached with {@link com.aventstack.extentreports.ExtentReports} which
	 * modifies the look and feel of the Extent Report.<br>
	 * 
	 * @param spark -
	 *              {@link com.aventstack.extentreports.reporter.ExtentSparkReporter}
	 *              instance attached with
	 *              {@link com.aventstack.extentreports.ExtentReports}
	 */

	static void getCustomizedExtentReport(ExtentSparkReporter spark) {



			spark.config().setTheme(Theme.DARK);
			spark.config().setTimelineEnabled(false);
			spark.config().setJs(ExtentHelper.customizedJSForSparkReport());



	}

	/**
	 * Sets 'Document Title' in
	 * {@link com.aventstack.extentreports.reporter.ExtentSparkReporter} instance
	 * attached with {@link com.aventstack.extentreports.ExtentReports} which
	 * reflects in the Extent Report.<br>
	 * 
	 * 
	 * @param spark         -
	 *                      {@link com.aventstack.extentreports.reporter.ExtentSparkReporter}
	 *                      instance attached with
	 *                      {@link com.aventstack.extentreports.ExtentReports}
	 * @param documentTitle - title supposed to be displayed in Extent Report
	 */

	static void setDocumentTitleForExtentReport(ExtentSparkReporter spark, String documentTitle) {

		spark.config().setDocumentTitle(documentTitle);

	}

	/**
	 * Sets 'Report Name' in
	 * {@link com.aventstack.extentreports.reporter.ExtentSparkReporter} instance
	 * attached with {@link com.aventstack.extentreports.ExtentReports} which
	 * reflects in the Extent Report.<br>
	 * 
	 * @param spark      -
	 *                   {@link com.aventstack.extentreports.reporter.ExtentSparkReporter}
	 *                   instance attached with
	 *                   {@link com.aventstack.extentreports.ExtentReports}
	 * @param reportName - Report name supposed to be displayed in Extent Report
	 */

	static void setReportNameForExtentReport(ExtentSparkReporter spark, String reportName) {

		spark.config().setReportName(reportName);

	}



	static void setViewingOrder(ExtentSparkReporter spark, ViewName[] viewname) {

		spark.viewConfigurer().viewOrder().as(viewname).apply();

	}
	
	/**
	 * 
	 * Attaches {@link com.aventstack.extentreports.reporter.ExtentSparkReporter}
	 * instance with {@link com.aventstack.extentreports.ExtentReports}<br>
	 * 
	 * @param extent - {@link com.aventstack.extentreports.ExtentReports} instance
	 * @param spark  -
	 *               {@link com.aventstack.extentreports.reporter.ExtentSparkReporter}
	 *               instance attached with
	 *               {@link com.aventstack.extentreports.ExtentReports}
	 */

	static void attachReporter(ExtentReports extent, ExtentSparkReporter spark) {

		extent.attachReporter(spark);
		extent.setReportUsesManualConfiguration(true);
	}

	

}
