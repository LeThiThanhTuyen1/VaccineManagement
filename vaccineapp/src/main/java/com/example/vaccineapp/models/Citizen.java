package com.example.vaccineapp.models;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String fullName;
    private String phoneNumber;
    private String email;
    private String citizenGroup;
    private Date dateOfBirth;
    private String gender;
    private String address;
    private Date createdAt;
        
    // Getters và Setters
}
