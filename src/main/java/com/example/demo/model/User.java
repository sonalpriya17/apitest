package com.example.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "userData")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @Id
    private ObjectId _id;
    private String username;
    private String email;
    private String passwordHash;
    private Profile profile;
    private List<Contact> contacts;
    private List<Purchase> purchaseHistory;
    private Date createdAt;
    private Date updatedAt;

    @Data
    public static class Profile {
        private String firstName;
        private String lastName;
        private Date dob;
        private List<Address> addresses;
    }

    @Data
    public static class Address {
        private String type;
        private String street;
        private String city;
        private String state;
        private String postalCode;
    }

    @Data
    public static class Contact {
        private String type;
        private String number;
    }

    @Data
    public static class Purchase {
        private ObjectId productId;
        private String productName;
        private Date purchaseDate;
        private Double amount;
    }
}
