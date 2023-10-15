package com.example.demo.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

@Component
@Profile("testrunner")
public class TestRunner implements CommandLineRunner {

    @Autowired
    private SpringObjectFactory springObjectFactory;

    @Override
    public void run(String... args) {
        System.out.println("=============> Processing task...");
        // Your task worker logic here

        // Run the TestNG test
        TestNG testNG = new TestNG();
        
        testNG.setObjectFactory(springObjectFactory);

        TestListenerAdapter tla = new TestListenerAdapter() {
            @Override
            public void onTestFailure(ITestResult result) {
                System.out.println(result.getThrowable());
            }
        };
        testNG.addListener(tla);
        List<String> suites = new ArrayList<>();
        suites.add("src/main/resources/testNG.xml");
        testNG.setTestSuites(suites);
        testNG.run();

        // Print names of successful tests
        tla.getPassedTests().forEach(result -> System.out.println("Successful test: " + result.getName()));
        // Print names of failed tests
        tla.getFailedTests().forEach(result -> System.out.println("Failed test: " + result.getName()));
    }
}