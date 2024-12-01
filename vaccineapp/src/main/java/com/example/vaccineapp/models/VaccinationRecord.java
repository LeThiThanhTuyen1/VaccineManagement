package com.example.vaccineapp.models;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class VaccinationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "citizen_id")
    private Citizen citizen;

    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;

    private Date vaccinationDate;
    private Long doseNumber;
    private Date createdAt;
    
    // Getters và Setters
}
