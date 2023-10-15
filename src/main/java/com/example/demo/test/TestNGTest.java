package com.example.demo.test;

import com.example.demo.model.TestData;
import com.example.demo.repository.TestRepository;
import com.example.demo.service.TestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TestNGTest {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestService testService;

    private String baseURI;
    private String basePath;

    @BeforeClass
    public void setup() {
        baseURI = "https://jsonplaceholder.typicode.com";
        basePath = "/posts";
    }

  
    @Test
    public void testPostRequest() {
        // Get the test data from MongoDB
        TestData testDataPojo = testRepository.findAll().get(0);
        System.out.println("----------i am testPostRequest-----------");

        List<TestData> testDataItems = testService.getAllTestData();

        ObjectMapper objectMapper = new ObjectMapper();

        // Print JSON of each item in testDataItems
        testDataItems.forEach(item -> {
            try {
                System.out.println(objectMapper.writeValueAsString(item));
            } catch (JsonProcessingException e) {
                System.out.println(e);
            }
        });
        // Prepare the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", testDataPojo.getTitle());
        requestBody.put("body", testDataPojo.getBody());
        requestBody.put("userId", testDataPojo.getUserId());

        // Send the POST request
        Response response = RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(baseURI + basePath);

        // Assert the response
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("title"), testDataPojo.getTitle());
        Assert.assertEquals(response.jsonPath().getString("body"), testDataPojo.getBody());
        Assert.assertEquals(response.jsonPath().getInt("userId"), testDataPojo.getUserId());
    }

    @Test
    public void testTestNGONE() {
        // Get the test data from MongoDB
        System.out.println("I am happy 1");
    }
    @Test
    public void testTestNGTWO() {
        // Get the test data from MongoDB
        System.out.println("I am happy 2");
    }
    @Test
    public void testTestNGTHREE() {
        // Get the test data from MongoDB
        System.out.println("I am happy 3");
    }
    @Test
    public void testTestNGFOUR() {
        // Get the test data from MongoDB
        Assert.assertEquals(1, 2);
    }
}
