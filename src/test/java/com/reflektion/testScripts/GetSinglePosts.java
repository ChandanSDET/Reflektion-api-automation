package com.reflektion.testScripts;

import com.reflektion.dto.PostsDTO;
import com.reflektion.utils.InitTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetSinglePosts extends InitTest {
    @Test
    public void testGetSinglePosts() {
        try {
            extentLogger = extentReport.createTest("Validate get single posts endpoint");
            setExtentTest(extentLogger);
            Response response = httpRequest.get("/posts/1");
            getExtentTest().info("Response body is:" + response.prettyPrint());
            PostsDTO getPostsResponse = response.getBody().as(PostsDTO.class);
            assertThat(response.getStatusCode(), equalTo(200));
            getExtentTest().pass("Status code: " + response.getStatusCode() + " is successfully verified");
            assertThat(getPostsResponse.getId(), equalTo(1));
            getExtentTest().pass("Id: " + getPostsResponse.getId() + " is successfully verified");
            assertThat(response.getBody().asString(), matchesJsonSchemaInClasspath("postSchema.json"));
            getExtentTest().pass("Get single posts schema is successfully verified");
        } catch (Exception | AssertionError e) {
            getExtentTest().fail(e);
            throw e;
        }
    }
}