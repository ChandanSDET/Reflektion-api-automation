package com.reflektion.testScripts;

import com.google.gson.Gson;
import com.reflektion.dto.PostsDTO;
import com.reflektion.utils.InitTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UpdatePosts extends InitTest {

    @Test
    public void testUpdatePosts() {
        try {
            extentLogger = extentReport.createTest("Validate update posts endpoint");
            setExtentTest(extentLogger);
            PostsDTO updatePostPayload = new PostsDTO(1, 1, "abd", "xyz");
            getExtentTest().info("Update payload is: " + new Gson().toJson(updatePostPayload));
            Response response = httpRequest
                    .body(updatePostPayload)
                    .put("/posts/1");
            getExtentTest().info("Response body is:" + response.prettyPrint());
            PostsDTO getPostsResponse = response.getBody().as(PostsDTO.class);
            assertThat(response.getStatusCode(), equalTo(200));
            getExtentTest().pass("Status code: " + response.getStatusCode() + " is successfully verified");
            assertThat(getPostsResponse.getTitle(), equalTo("abd"));
            getExtentTest().pass("Title: " + getPostsResponse.getTitle() + " is successfully updated");
            assertThat(getPostsResponse.getBody(), equalTo("xyz"));
            getExtentTest().pass("Body: " + getPostsResponse.getBody() + " is successfully updated");
            assertThat(getPostsResponse.getUserId(), equalTo(1));
            getExtentTest().pass("UsedId: " + getPostsResponse.getUserId() + " is successfully updated");
            assertThat(response.getBody().asString(), matchesJsonSchemaInClasspath("postSchema.json"));
            getExtentTest().pass("Update posts schema is successfully verified");
        } catch (Exception | AssertionError e) {
            getExtentTest().fail(e);
            throw e;
        }
    }
}