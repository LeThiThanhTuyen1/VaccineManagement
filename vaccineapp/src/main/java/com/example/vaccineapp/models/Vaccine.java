package com.example.vaccineapp.models;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String vaccineName;
    private String manufacturer;
    private int dosesRequired;
    private Date createdAt;
    // Getters và Setters
}
