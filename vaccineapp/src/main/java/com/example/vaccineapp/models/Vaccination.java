package com.example.vaccineapp.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "vaccinations")
public class Vaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "citizen_id", nullable = false)
    private Citizen citizen;

    @ManyToOne
    @JoinColumn(name = "vaccine_id", nullable = false)
    private Vaccine vaccine;

    @Column(name = "vaccination_date", nullable = false)
    private LocalDate vaccinationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    public enum Status {
    	PENDING("Chờ xử lý"),
        COMPLETED("Đã tiêm"),
        CANCELLED("Đã hủy");
    	
    	private final String displayName;

        Status(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public LocalDate getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(LocalDate vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}