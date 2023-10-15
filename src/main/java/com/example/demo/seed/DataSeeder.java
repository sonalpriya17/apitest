package com.example.demo.seed;

import com.example.demo.model.TestData;
import com.example.demo.repository.TestRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;

@Component
public class DataSeeder {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void seedTestData() {
        try {
            testRepository.deleteAll();

            // Handle multiple test data files
            File folder = ResourceUtils.getFile("classpath:testdata/");
            File[] listOfFiles = folder.listFiles();

            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().endsWith(".json")) {
                    ObjectMapper mapper = new ObjectMapper();
                    List<TestData> testDataList = mapper.readValue(file, new TypeReference<List<TestData>>(){});
                    testDataList.forEach(testData -> testRepository.save(testData));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
