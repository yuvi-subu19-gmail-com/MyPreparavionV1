package com.automation.mypreparation.utiities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {

	ExtentHtmlReporter reporter;
	ExtentReports extent;
	ExtentTest test;
	WebDriver idriver;

	public void onStart(ITestContext testContext) {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy_hhmmss");
		String DateTimeStamp = sdf.format(new Date());
		reporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "//test-output//MyPreparationV1Report" + DateTimeStamp + ".html");
		reporter.loadXMLConfig(System.getProperty("user.dir") + "//extent-config.xml");

		reporter.config().setReportName("Automation Test Report for MyPreparation App");
		reporter.config().setDocumentTitle("Automation Report");
		reporter.config().enableTimeline(true);
		reporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Host Name", "Local Host");
		extent.setSystemInfo("Env", "QA");
		extent.setSystemInfo("user", "Yuvaraj");

	}

	public void onTestSuccess(ITestResult tr) {

		test = extent.createTest(tr.getName());
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));

	}

	public void onTestFailure(ITestResult tr) {

		test = extent.createTest(tr.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		test.log(Status.ERROR, tr.getThrowable());
		String methodName = tr.getName();
		ITestContext context = tr.getTestContext();
		idriver = (WebDriver) context.getAttribute("webDriver");
		String path = takeScreenshot(methodName, idriver);
		try {
			test.fail("Screenshot is shown below                 " + test.addScreenCaptureFromPath(path));
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public String takeScreenshot(String method, WebDriver rdriver) {

		String screenshotPath = System.getProperty("user.dir") + "//Screenshots//" + method + ".png";

		File dest = new File(screenshotPath);

		File src = ((TakesScreenshot) rdriver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return screenshotPath;

	}

	public void onTestSkipped(ITestResult tr) {
		test = extent.createTest(tr.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}
