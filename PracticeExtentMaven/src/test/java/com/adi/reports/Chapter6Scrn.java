package com.adi.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Chapter6Scrn {
	static WebDriver driver;
	public static void main(String[] args) throws IOException {
	      ExtentReports extentReports = new ExtentReports();
	      File file=new File("report.html");
	      ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
	      extentReports.attachReporter(sparkReporter);
	      
	      WebDriverManager.chromedriver().setup();
	      driver=new ChromeDriver();
	      driver.get("https://www.google.com");
	      String base64Code=captureScreenshot();
	      String path=captureScreenshot("Google.jpg");
	      
	      extentReports
	      .createTest("Screenshot test1", "This is for attaching screenshot for the test")
	      .info("This is an info message")
	      .addScreenCaptureFromBase64String(base64Code);
	      
	      extentReports.flush();
	      driver.quit();
	      Desktop.getDesktop().browse(new File("report.html").toURI());

	 }     
	
	 public static String captureScreenshot() {
		 TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
		 String base64Code=takesScreenshot.getScreenshotAs(OutputType.BASE64);
		 System.out.println("Screenshot saved successfully");
		 return base64Code;
	 	}
	 
	 public static String captureScreenshot(String fileName) {
		 TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
		 File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
		 File destFile=new File("./Screenshots/" +fileName);
		 try {
			 FileUtils.copyFile(sourceFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 System.out.println("Screenshot saved successfully");
		 return destFile.getAbsolutePath();
		 	      
	}

}
