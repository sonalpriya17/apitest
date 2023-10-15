// com.example.demo.DemoApplication.java

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.seed.DataSeeder;
import com.example.demo.service.TestService;
import java.util.Arrays;
import javax.annotation.PostConstruct; 

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DemoApplication.class);

        // Set WebApplicationType based on profile
        if (Arrays.stream(args).noneMatch(arg -> arg.contains("spring.profiles.active=web"))) {
            app.setWebApplicationType(WebApplicationType.NONE);
        }

        app.run(args);
    }

    @Configuration
    @Profile("web")
    @ComponentScan(basePackages = "com.example.demo.web")
    static class WebConfig {
        // Additional web-related configurations if needed
        @Autowired
        private DataSeeder dataSeeder;

        @PostConstruct
        public void seedData() {
            dataSeeder.seedTestData();
        }
    }

    @Configuration
    @Profile("testrunner")
    @ComponentScan(basePackages = "com.example.demo.test")
    static class TestRunnerConfig {
        // Additional worker-related configurations if needed
        @Autowired
        private DataSeeder dataSeeder;

        @PostConstruct
        public void seedData() {
            dataSeeder.seedTestData();
        }
    }
}
