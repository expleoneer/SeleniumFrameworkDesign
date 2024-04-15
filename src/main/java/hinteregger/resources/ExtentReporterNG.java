package hinteregger.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports getReportObject(){
        String path = System.getProperty("user.dir") + "\\reports\\index.html"; 
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
        extentSparkReporter.config().setReportName("WebAutomationResults");
        extentSparkReporter.config().setDocumentTitle("Test Results");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Tester", "David Hinteregger");      
        
        return extentReports;
    }
}
