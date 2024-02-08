package com.adi.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Chapter4 {

	public static void main(String[] args) throws IOException {
	      ExtentReports extentReports = new ExtentReports();
	      File file=new File("report.html");
	      ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
	      extentReports.attachReporter(sparkReporter);
	      
	      
	      extentReports
	      .createTest("Test1")
	      .log(Status.INFO, "info1")
	      .log(Status.INFO, "info2")
	      .log(Status.PASS, "PAss")
	      .log(Status.INFO, "info1")
	      .log(Status.WARNING, "info1")
	      .log(Status.INFO, "info1")
	      .log(Status.SKIP, "Skip")
	      .log(Status.FAIL, "info1");
	      
	      extentReports.flush();
	      Desktop.getDesktop().browse(new File("report.html").toURI());
	      
	}

}
