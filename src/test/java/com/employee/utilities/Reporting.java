package com.employee.utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Reporting extends TestListenerAdapter {
	public  ExtentSparkReporter spark;
	public  ExtentReports extent;
	public  ExtentTest test;

	public  void onStart(ITestContext testContext){

		String timestamp = new SimpleDateFormat("yyyy-dd-HH.mm.ss").format(new Date());
		String repName = "TestReport-" + timestamp + ".html";
		
		spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/target/ExtentReport/" + repName);
		try {
			spark.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		 

		spark.config().setDocumentTitle("Automation Test Report");
		spark.config().setReportName("Rest API Report");
		spark.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(spark);

		extent.setSystemInfo("HostName", "MyHost");
		extent.setSystemInfo("Project Name", "REST API");
		extent.setSystemInfo("Tester", "Prasanth");
		extent.setSystemInfo("OS", "Windows 10");

	}

	public  void onFinish(ITestContext testContext) {
		extent.flush();
	}

	public void onTestSuccess(ITestResult tr) {
		
		test = extent.createTest(tr.getMethod().getRealClass().getName());
		
		test.log(Status.PASS, MarkupHelper.createLabel(tr.getName() + " - Test Case Passed", ExtentColor.GREEN));
  
	}

	public void onTestSkipped(ITestResult tr) {
		test = extent.createTest(tr.getMethod().getRealClass().getName());
		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName() + " - Test Case Skipped", ExtentColor.ORANGE));

	}

	public void onTestFailure(ITestResult tr) {

		test = extent.createTest(tr.getMethod().getRealClass().getName());
		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName() + " - Test Case Failed", ExtentColor.RED));
		test.log(Status.FAIL, MarkupHelper.createLabel(tr.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		

	}

}
