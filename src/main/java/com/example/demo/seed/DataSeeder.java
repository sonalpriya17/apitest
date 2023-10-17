package com.example.demo.seed;

import com.example.demo.model.Customer;
import com.example.demo.model.TestData;
import com.example.demo.model.User;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.TestRepository;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class DataSeeder {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerH2DataRepository;

    public void seed() {
        try {
            File testDataDir = ResourceUtils.getFile("classpath:testdata");
            File[] listOfFiles = testDataDir.listFiles();

            for (File file : listOfFiles) {
                System.out.println("file name:------->"+file.getAbsolutePath());
                if (file.isFile() && file.getName().endsWith(".json")) {
                    mongoDBDataSeeder(file);
                } else if (file.isFile() && file.getName().endsWith(".csv")) {
                    H2DataSeeder(file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mongoDBDataSeeder(File file) {
        testRepository.deleteAll();
        userRepository.deleteAll();

        try {
            ObjectMapper mapper = new ObjectMapper();
            List<TestData> testDataList = mapper.readValue(file, new TypeReference<List<TestData>>(){});
            testDataList.forEach(testData -> testRepository.save(testData));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void H2DataSeeder(File file) {
        customerH2DataRepository.deleteAll();

        try (CSVReader reader = new CSVReader(new FileReader(file.getPath()))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                if (line[0].equalsIgnoreCase("title") && line[1].equalsIgnoreCase("body")) {
                    continue;
                }

                Customer customer = new Customer();
                customer.setTitle(line[0]);
                customer.setBody(line[1]);

                try {
                    customer.setUserId(Long.parseLong(line[2]));
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing user ID: " + line[2]);
                }

                customerH2DataRepository.save(customer);
            }
        } catch (IOException |CsvValidationException e) {
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
