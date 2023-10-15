package com.example.demo.service;

import com.example.demo.model.TestData;
import com.example.demo.repository.TestRepository;
import com.example.demo.test.TestNGTest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.testng.TestNG;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public List<TestData> getAllTestData() {
        return testRepository.findAll();
    }

    public void seedTestData() {
        try {
            testRepository.deleteAll();

            File file = ResourceUtils.getFile("classpath:test.json");
            ObjectMapper mapper = new ObjectMapper();
            List<TestData> testDataList = mapper.readValue(file, new TypeReference<List<TestData>>(){});
            testDataList.forEach(testData -> testRepository.save(testData));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String runTest(String testJson) throws Exception {
        // Parse the JSON input
        ObjectMapper mapper = new ObjectMapper();
        TestData testDataPojo = mapper.readValue(testJson, TestData.class);

        // Save the test to MongoDB
        testRepository.save(testDataPojo);

        // Return the test result
        return "Test run successfully";
    }

    public String updateTest(String testJson) throws Exception {
        // Parse the JSON input
        ObjectMapper mapper = new ObjectMapper();
        TestData testDataPojo = mapper.readValue(testJson, TestData.class);

        // Update the test in MongoDB
        TestData existingTestDataPojo = testRepository.findById(testDataPojo.getId()).orElseThrow(() -> new Exception("Test not found"));
        existingTestDataPojo.setTitle(testDataPojo.getTitle());
        existingTestDataPojo.setBody(testDataPojo.getBody());
        existingTestDataPojo.setUserId(testDataPojo.getUserId());
        testRepository.save(existingTestDataPojo);

        // Run the TestNG test
        TestNG testNG = new TestNG();
        testNG.setTestClasses(new Class[] { TestNGTest.class });
        testNG.run();

        // Return the test result
        return "Test updated and run successfully";
    }
}
