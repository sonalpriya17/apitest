package com.example.demo.seed;

import com.example.demo.model.TestData;
import com.example.demo.model.User;
import com.example.demo.repository.TestRepository;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.File;
import java.util.List;

@Component
public class DataSeeder {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private UserRepository userRepository;

    public void seed() {
        try {
            File testDataDir = new File("src/main/resources/testdata");
            File[] listOfFiles = testDataDir.listFiles();

            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().endsWith(".json")) {
                    seedDataFile(file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void seedDataFile(File file) throws RuntimeException {
        switch (file.getName()) {
            case "test_data.json": seedTestData(file); break;
            case "user.json": seedUser(file); break;
            default: throw new RuntimeException("invalid file");
        }
    }

    private void seedTestData(File file) {
        testRepository.deleteAll();

        try {
            ObjectMapper mapper = new ObjectMapper();
            List<TestData> testDataList = mapper.readValue(file, new TypeReference<List<TestData>>(){});
            testDataList.forEach(testData -> testRepository.save(testData));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void seedUser(File file) {
        userRepository.deleteAll();

        try {
            ObjectMapper mapper = new ObjectMapper();
            List<User> testDataList = mapper.readValue(file, new TypeReference<List<User>>(){});
            testDataList.forEach(testData -> userRepository.save(testData));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
