package com.reflektion.testScripts;

import com.reflektion.dto.PostsDTO;
import com.reflektion.utils.InitTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetAllPosts extends InitTest {

    @Test
    public void testGetAllPosts() {
        try {
            extentLogger = extentReport.createTest("Validate get all posts endpoint");
            setExtentTest(extentLogger);
            Response response = httpRequest.get("/posts");
            getExtentTest().info("Response body is:" + response.prettyPrint());
            PostsDTO getPostsResponse[] = response.getBody().as(PostsDTO[].class);
            assertThat(response.getStatusCode(), equalTo(200));
            getExtentTest().pass("Status code: " + response.getStatusCode() + " is successfully verified");
            assertThat(getPostsResponse.length, equalTo(100));
            getExtentTest().pass("Response retuned " + getPostsResponse.length + " records");
            assertThat(response.getBody().asString(), matchesJsonSchemaInClasspath("getAllPostSchema.json"));
            getExtentTest().pass("Get All posts schema is successfully verified");
        } catch (Exception | AssertionError e) {
            getExtentTest().fail(e);
            throw e;
        }
    }
}
