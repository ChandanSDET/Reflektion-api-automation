package com.reflektion.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.util.HashMap;
import java.util.Map;

public class InitTest {
    protected RequestSpecification httpRequest;
    public ExtentReports extentReport;
    public ExtentTest extentLogger;
    public static ThreadLocal<ExtentTest> exLogger;

    @BeforeClass
    public void inItSuite() {
        exLogger = new ThreadLocal<ExtentTest>();
        extentReport = ExtentReportManager.generateExtentReport();
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
        httpRequest = RestAssured.given().headers(this.getHeadersContent()).log().all();
    }

    private Map getHeadersContent() {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("Content-type", ContentType.JSON);
        headerMap.put("charset", "UTF-8");
        return headerMap;
    }

    @AfterMethod
    public void flushTest() {
        extentReport.flush();
    }

    public void setExtentTest(ExtentTest et) {
        exLogger.set(et);
    }

    public ExtentTest getExtentTest() {
        return exLogger.get();
    }
}
