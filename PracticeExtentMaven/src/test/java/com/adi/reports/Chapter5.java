package com.adi.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Chapter5 {

	public static void main(String[] args) throws IOException {
	      ExtentReports extentReports = new ExtentReports();
	      File file=new File("report.html");
	      ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);
	      extentReports.attachReporter(sparkReporter);
	      
	      
	      extentReports
	      .createTest("Text based test")
	      .log(Status.INFO, "<b>info1</b>")
	      .log(Status.INFO, "<b><i>info2</i></b>")
	      .log(Status.PASS, "Pass");
	      
	      String xmlData= "<menu id=\"file\" value=\"File\">\r\n"
	      		+ "  <popup>\r\n"
	      		+ "    <menuitem value=\"New\" onclick=\"CreateNewDoc()\" />\r\n"
	      		+ "    <menuitem value=\"Open\" onclick=\"OpenDoc()\" />\r\n"
	      		+ "    <menuitem value=\"Close\" onclick=\"CloseDoc()\" />\r\n"
	      		+ "  </popup>\r\n"
	      		+ "</menu>";
	      
	      String jsonData= "{\"menu\": {\r\n"
	      		+ "  \"id\": \"file\",\r\n"
	      		+ "  \"value\": \"File\",\r\n"
	      		+ "  \"popup\": {\r\n"
	      		+ "    \"menuitem\": [\r\n"
	      		+ "      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\r\n"
	      		+ "      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\r\n"
	      		+ "      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\r\n"
	      		+ "    ]\r\n"
	      		+ "  }\r\n"
	      		+ "}}";
	      
	      extentReports.createTest("XML Based Test")
	      .info(MarkupHelper.createCodeBlock(xmlData, CodeLanguage.XML));
	      
	      
	      extentReports.createTest("JSON Based Test")
	      .log(Status.INFO, MarkupHelper.createCodeBlock(jsonData, CodeLanguage.JSON));
	      
	      List<String> listData=new ArrayList<>();
	      listData.add("Budrukkar");
	      listData.add("Aditya");
	      listData.add("Prabhakar");
	      
	      Map<Integer, String> mapData= new HashMap<>();
	      mapData.put(101, "Budrukar");
	      mapData.put(102, "Aditya");
	      mapData.put(103, "Prabhakar");
	      
	      Set<Integer> setData=mapData.keySet();

	      extentReports.createTest("List Based Test")
	      .info(MarkupHelper.createCodeBlock(xmlData, CodeLanguage.XML));
	      
	      extentReports
	      .createTest("List based Test")
	      .info(MarkupHelper.createOrderedList(listData))
	      .info(MarkupHelper.createUnorderedList(listData));
	      
	      extentReports
	      .createTest("Set based Test")
	      .info(MarkupHelper.createOrderedList(setData))
	      .info(MarkupHelper.createUnorderedList(setData));
	      
	      extentReports
	      .createTest("List based Test")
	      .info(MarkupHelper.createOrderedList(mapData))
	      .info(MarkupHelper.createUnorderedList(mapData));
	      
	      Throwable t=new RuntimeException("This is a custom exception");
	      extentReports
	      .createTest("Exception Test1")
	      .info(t);       
	      
	      extentReports.flush();
	      Desktop.getDesktop().browse(new File("report.html").toURI());
	      
	}

}
