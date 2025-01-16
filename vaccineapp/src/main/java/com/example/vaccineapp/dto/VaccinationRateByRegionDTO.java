package com.example.vaccineapp.dto;

public class VaccinationRateByRegionDTO {
	private String regionName;
	private long totalCitizens;
	private long vaccinatedCitizens;
	private double vaccinationRate;

	// Constructors
	public VaccinationRateByRegionDTO(String regionName, long totalCitizens, long vaccinatedCitizens,
			double vaccinationRate) {
		this.regionName = regionName;
		this.totalCitizens = totalCitizens;
		this.vaccinatedCitizens = vaccinatedCitizens;
		this.vaccinationRate = vaccinationRate;
	}

	// Getters and Setters
	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public long getTotalCitizens() {
		return totalCitizens;
	}

	public void setTotalCitizens(long totalCitizens) {
		this.totalCitizens = totalCitizens;
	}

	public long getVaccinatedCitizens() {
		return vaccinatedCitizens;
	}

	public void setVaccinatedCitizens(long vaccinatedCitizens) {
		this.vaccinatedCitizens = vaccinatedCitizens;
	}

	public double getVaccinationRate() {
		return vaccinationRate;
	}

	public void setVaccinationRate(double vaccinationRate) {
		this.vaccinationRate = vaccinationRate;
	}
}
