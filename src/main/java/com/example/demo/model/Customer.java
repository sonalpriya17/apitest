// src/main/java/com/example/demo/model/Customer.java

package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String body;
    private Long userId; // Assuming 'userId' is a unique identifier besides the primary key

    // You need to have an empty constructor for JPA
    public Customer() {
    }
}