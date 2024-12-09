package com.example.vaccineapp.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "citizens")
public class Citizen {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "full_name", nullable = false)
    private String fullName;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Ward ward;
    
    @Column(name = "address_detail", columnDefinition = "varchar(255)")
    private String addressDetail;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "target_group")
    private TargetGroup targetGroup;
        
    public Citizen() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Ward getWard() {
		return ward;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public TargetGroup getTargetGroup() {
		return targetGroup;
	}

	public void setTargetGroup(TargetGroup targetGroup) {
		this.targetGroup = targetGroup;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	
	@Transient
	private List<VaccinationHistory> vaccinationHistory;

	public List<VaccinationHistory> getVaccinationHistory() {
	    return vaccinationHistory;
	}

	public void setVaccinationHistory(List<VaccinationHistory> vaccinationHistory) {
	    this.vaccinationHistory = vaccinationHistory;
	}

	public Citizen(String fullName, String phoneNumber, Ward ward, String addressDetail, LocalDate dateOfBirth,
			TargetGroup targetGroup) {
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.ward = ward;
		this.addressDetail = addressDetail;
		this.dateOfBirth = dateOfBirth;
		this.targetGroup = targetGroup;
	}

	public Citizen(Long id, String fullName, String phoneNumber, Ward ward, String addressDetail,
			LocalDate dateOfBirth, TargetGroup targetGroup) {
		this.id = id;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.ward = ward;
		this.addressDetail = addressDetail;
		this.dateOfBirth = dateOfBirth;
		this.targetGroup = targetGroup;
	}
	
	public enum TargetGroup {
	    CHILD("TRẺ EM"),
	    ELDERLY("NGƯỜI CAO TUỔI"),
	    PREGNANT_WOMEN("PHỤ NỮ MANG THAI"),
	    OTHER("KHÁC");

	    private final String displayName;

	    TargetGroup(String displayName) {
	        this.displayName = displayName;
	    }

	    public String getDisplayName() {
	        return displayName;
	    }
	}
}
