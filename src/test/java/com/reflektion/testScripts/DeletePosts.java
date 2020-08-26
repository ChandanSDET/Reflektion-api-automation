package com.reflektion.testScripts;

import com.reflektion.utils.InitTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeletePosts extends InitTest {
    @Test
    public void testDeletePosts() {
        try {
            extentLogger = extentReport.createTest("Validate delete posts endpoint");
            setExtentTest(extentLogger);
            Response response = httpRequest.delete("posts/1");
            getExtentTest().info("Response body is:" + response.prettyPrint());
            assertThat(response.getStatusCode(), equalTo(200));
            getExtentTest().pass("Status code: " + response.getStatusCode() + " is successfully verified");
        } catch (Exception | AssertionError e) {
            getExtentTest().fail(e);
            throw e;
        }
    }
}
