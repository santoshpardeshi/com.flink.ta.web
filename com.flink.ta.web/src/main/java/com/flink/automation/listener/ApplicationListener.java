package com.flink.automation.listener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.flink.automation.base.BaseTest;
import com.flink.automation.helper.CustomizedException;
import com.flink.automation.utils.ExtentManager;
import com.flink.automation.utils.FrameworkConfigurationReader;


public class ApplicationListener implements ITestListener{

	/** extent. */
	private static ExtentReports extent = ExtentManager.createInstance();

	/** parentTest. */
	private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>();

	/** test. */
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	/** parent. */
	private static ExtentTest parent;

	/** child. */
	private static ExtentTest child;

	/** driver. */
	private WebDriver driver;

	@Override
	public synchronized void onStart(final ITestContext context) {
		System.out.println("OnStart begin");
	}

	@Override
	public synchronized void onFinish(final ITestContext context) {
		extent.flush();
	}

	@Override
	public synchronized void onTestStart(final ITestResult result) {
		parent = extent.createTest(result.getTestClass().getRealClass().getSimpleName());
		parentTest.set(parent);
		child = parentTest.get().createNode(result.getMethod().getMethodName(), result.getMethod().getDescription());
		test.set(child);
		test.get().assignCategory(result.getTestClass().getRealClass().getSimpleName());
	}

	@Override
	public synchronized void onTestSuccess(final ITestResult result) {
		test.get().pass("Test passed");
	}

	@Override
	public synchronized void onTestFailure(final ITestResult result) {
		test.get().fail(result.getThrowable());
		this.driver = ((BaseTest) result.getInstance()).getBrowser().getDriver();
		String screenshotPath = "";
		
		try {
			screenshotPath = takeScreenShot(this.driver, result);
		} catch (IOException e) {
			new CustomizedException(e.getMessage());
		}
		
		try {
			test.get().fail("details").addScreenCaptureFromPath(screenshotPath);
		} catch (Exception e) {
			new CustomizedException(e.getMessage());
		}
	}

	@Override
	public synchronized void onTestSkipped(final ITestResult result) {
		if (test != null) {
			if (result != null) {
				new CustomizedException("test case is skipped.");
			}
			test.get().skip(result.getThrowable());
		}
	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(final ITestResult result) {
		// Default, Update as required.
	}

	/**
	 * This method gets extend test.
	 * 
	 * @return Object extend test
	 */
	public static ExtentTest getExtentTest() {
		return test.get();
	}

	/**
	 * This method is used to take screen shot.
	 * 
	 * @param webDriver object web driver
	 * @param iTestResult object iTestResult
	 * @return path of file (screen shot)
	 * @throws IOException IO exception.
	 */
	public String takeScreenShot(final WebDriver webDriver, final ITestResult iTestResult) throws IOException {
		final FrameworkConfigurationReader frameworkConfigurationReader = FrameworkConfigurationReader.getInstance();
		final String screenshotFile = iTestResult.getTestClass().getRealClass().getSimpleName() + "_"
				+ iTestResult.getName() + "_"
				+ new SimpleDateFormat(frameworkConfigurationReader.getProperty("report.name.timestamp.format"),
					Locale.ENGLISH).format(new Date())
				+ "." + frameworkConfigurationReader.getProperty("screenshot.extension");
		final String screenshotPath = createReportsScreenshotsDirectory() + File.separator + screenshotFile;
		final File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(screenshotPath));
		return screenshotPath;
	}

	/**
	 * This method is used to create report screen shot directory.
	 * 
	 * @return path of report directory
	 */
	private String createReportsScreenshotsDirectory() {
		final String reportsScreenshotsDirectoryPath = System.getProperty("user.dir") + File.separator
				+ "test-output" + File.separator + "Reports" + File.separator + "ReportsScreenshots";
		final File directory = new File(reportsScreenshotsDirectoryPath);
		if (!directory.exists()) {
			try {
				FileUtils.forceMkdir(directory);
			} catch (IOException e) {
				new CustomizedException(e.getMessage());
			}
		}
		return reportsScreenshotsDirectoryPath;
	}

}
