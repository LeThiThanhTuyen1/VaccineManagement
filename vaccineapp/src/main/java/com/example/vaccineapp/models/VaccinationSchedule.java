package com.example.vaccineapp.models;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class VaccinationSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "citizen_id")
    private Citizen citizen;

    private Date scheduledDate;
    private String status; // Trạng thái như: Chờ tiêm, Đã tiêm
    private Date createdAt;
    
    // Getters và Setters
}
