package com.reflektion.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

	// public static ExtentHtmlReporterConfiguration htmlReporter;
	public static ExtentReports extent;

	private ExtentReportManager() {}

	public static synchronized ExtentReports generateExtentReport() {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/report/reflektion-api-report.html");
		if(extent == null) {
			extent = new ExtentReports();
		}
		extent.attachReporter(htmlReporter);
		htmlReporter.config().setDocumentTitle("Reflektion API Automation");
		htmlReporter.config().setReportName("Reflektion API Automation Report");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setTheme(Theme.DARK); 
		return extent;
	}

}
