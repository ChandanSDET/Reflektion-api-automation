package com.reflektion.testScripts;

import com.reflektion.utils.InitTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetInvalidPosts extends InitTest {

    @Test
    public void testGetInvalidPosts() {
        try {
            extentLogger = extentReport.createTest("Validate invalid posts endpoint");
            setExtentTest(extentLogger);
            Response response = httpRequest.get("/invalidposts");
            getExtentTest().info("Response body is:" + response.prettyPrint());
            assertThat(response.getStatusCode(), equalTo(404));
            getExtentTest().pass("Status code: " + response.getStatusCode() + " is successfully verified");
        } catch (Exception | AssertionError e) {
            getExtentTest().fail(e);
            throw e;
        }
    }
}
