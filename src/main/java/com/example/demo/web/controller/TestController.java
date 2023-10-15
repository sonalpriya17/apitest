package com.example.demo.web.controller;

import com.example.demo.model.TestData;
import com.example.demo.service.TestService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/testData")
    public ResponseEntity<Map<String, List<TestData>>> getAllTestData() {
        Map<String, List<TestData>> response = new HashMap<>();
        response.put("test_data_items", testService.getAllTestData());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/test")
    public ResponseEntity<String> runTest(@RequestBody String testJson) {
        try {
            String result = testService.runTest(testJson);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/test")
    public ResponseEntity<String> updateTest(@RequestBody String testJson) {
        try {
            String result = testService.updateTest(testJson);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
