package com.flink.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ExtentManager {
	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	public static ExtentReports createInstance() {

		FrameworkConfigurationReader frameworkConfigurationReader = FrameworkConfigurationReader.getInstance();
		if (null != frameworkConfigurationReader) {
			String fileName = frameworkConfigurationReader.getProperty("extent.report.name")
					+ "." + frameworkConfigurationReader.getProperty("extent.report.name.extension");

			ExtentSparkReporter htmlReporter = new ExtentSparkReporter(
					createExtentReportDirectory() + File.separator + fileName);
			htmlReporter.config().setTheme(Theme.STANDARD);
			htmlReporter.config().setDocumentTitle(fileName);
			htmlReporter.config().setEncoding("utf-8");
			htmlReporter.config().setReportName(fileName);
			extent = new ExtentReports();

			String reportAdditionalInfo = frameworkConfigurationReader.getProperty("extent.report.additional.info");
			if (null != reportAdditionalInfo) {
				String[] splitReportAdditionalInfo = reportAdditionalInfo.split(",");
				if (null != splitReportAdditionalInfo && splitReportAdditionalInfo.length > 0) {
					for (String individualInfo : splitReportAdditionalInfo) {
						if (null != individualInfo) {
							String[] splitIndividualInfo = individualInfo.split("-");
							if (null != splitIndividualInfo && splitIndividualInfo.length > 0) {
								extent.setSystemInfo(splitIndividualInfo[0], splitIndividualInfo[1]);
							}
						}
					}
				}
			}
			extent.attachReporter(htmlReporter);
		}
		return extent;
	}

	private static String createExtentReportDirectory() {
		String extentReportsDirectoryPath = System.getProperty("user.dir") + File.separator + "test-output"
				+ File.separator + "Reports" + File.separator + "ExtentReports";
		File directory = new File(extentReportsDirectoryPath);
		if (!directory.exists()) {
			try {
				FileUtils.forceMkdir(directory);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return extentReportsDirectoryPath;
	}
}
