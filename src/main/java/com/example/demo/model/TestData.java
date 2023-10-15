package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Id;

@Document(collection = "test")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestData {
   
    @Id
    private String id;
    private String title;
    private String body;
    private int userId;
}

