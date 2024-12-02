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
    
    public VaccinationRecord() {
		// TODO Auto-generated constructor stub
	}
    
    public VaccinationRecord(Long id, Citizen citizen, Vaccine vaccine, Date vaccinationDate, Long doseNumber,
			Date createdAt) {
		super();
		this.id = id;
		this.citizen = citizen;
		this.vaccine = vaccine;
		this.vaccinationDate = vaccinationDate;
		this.doseNumber = doseNumber;
		this.createdAt = createdAt;
	}
    
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
	public Date getVaccinationDate() {
		return vaccinationDate;
	}
	public void setVaccinationDate(Date vaccinationDate) {
		this.vaccinationDate = vaccinationDate;
	}
	public Long getDoseNumber() {
		return doseNumber;
	}
	public void setDoseNumber(Long doseNumber) {
		this.doseNumber = doseNumber;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
