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
    
    public VaccinationSchedule() {
		// TODO Auto-generated constructor stub
	}
    
    public VaccinationSchedule(Citizen citizen, Date scheduledDate, String status, Date createdAt) {
		super();
		this.citizen = citizen;
		this.scheduledDate = scheduledDate;
		this.status = status;
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

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
