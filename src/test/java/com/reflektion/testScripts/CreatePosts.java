package com.reflektion.testScripts;

import com.google.gson.Gson;
import com.reflektion.dto.PostsDTO;
import com.reflektion.utils.InitTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreatePosts extends InitTest {

    @Test
    public void testCreatePosts() {
        try {
            extentLogger = extentReport.createTest("Validate create posts endpoint");
            setExtentTest(extentLogger);
            getExtentTest().info("Request body is: {\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }");
            Response response = httpRequest
                    .body("{\n" +
                            "\"title\": \"foo\",\n" +
                            "\"body\": \"bar\",\n" +
                            "\"userId\": 1\n" +
                            "}")
                    .post("/posts");
            getExtentTest().info("Response body is:" + response.prettyPrint());
            PostsDTO getPostsResponse = response.getBody().as(PostsDTO.class);
            assertThat(response.getStatusCode(), equalTo(201));
            getExtentTest().pass("Status code: " + response.getStatusCode() + " is successfully verified");
            assertThat(getPostsResponse.getTitle(), equalTo("foo"));
            getExtentTest().pass("Title: " + getPostsResponse.getTitle() + " is successfully verified");
            assertThat(getPostsResponse.getBody(), equalTo("bar"));
            getExtentTest().pass("Body: " + getPostsResponse.getBody() + " is successfully verified");
            assertThat(getPostsResponse.getUserId(), equalTo(1));
            getExtentTest().pass("UsedId: " + getPostsResponse.getUserId() + " is successfully verified");
            assertThat(response.getBody().asString(), matchesJsonSchemaInClasspath("postSchema.json"));
            getExtentTest().pass("Create posts schema is successfully verified");
        } catch (Exception | AssertionError e) {
            getExtentTest().fail(e);
            throw e;
        }
    }
}
