// src/main/java/com/example/demo/test/testH2.java

package com.example.demo.test;

import com.example.demo.model.Customer;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class testH2 {

    @Autowiredg
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    @Test
    public void readCSVAndStoreInH2() throws IOException {
        // Read data from CSV
        try (Reader reader = Files.newBufferedReader(Paths.get(new ClassPathResource("testdata/customer_data.csv").getURI()))) {
            CsvToBean<Customer> csvToBean = new CsvToBeanBuilder<Customer>(reader)
                    .withType(Customer.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<Customer> customers = csvToBean.parse();

            // Store data in H2
            for (Customer customer : customers) {
                jdbcTemplate.update("INSERT INTO Customer (title, body, userId) VALUES (?, ?, ?)",
                        customer.getTitle(), customer.getBody(), customer.getUserId());
            }

            // Retrieve data from H2 and assert
            List<Customer> customersFromDB = jdbcTemplate.query("SELECT * FROM Customer",
                    (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("title"), rs.getString("body"), rs.getLong("userId")));

            Assert.assertEquals(customers, customersFromDB);
        }
    }
}