package com.example.vaccineapp.dto;

import java.time.LocalDate;

public class VaccinationReportDTO {
	private LocalDate date;
	private long totalVaccinations;
	private long successfulVaccinations;
	private long failedVaccinations;

	// Constructors
	public VaccinationReportDTO(LocalDate date, long totalVaccinations, long successfulVaccinations,
			long failedVaccinations) {
		this.date = date;
		this.totalVaccinations = totalVaccinations;
		this.successfulVaccinations = successfulVaccinations;
		this.failedVaccinations = failedVaccinations;
	}

	// Getters and Setters
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public long getTotalVaccinations() {
		return totalVaccinations;
	}

	public void setTotalVaccinations(long totalVaccinations) {
		this.totalVaccinations = totalVaccinations;
	}

	public long getSuccessfulVaccinations() {
		return successfulVaccinations;
	}

	public void setSuccessfulVaccinations(long successfulVaccinations) {
		this.successfulVaccinations = successfulVaccinations;
	}

	public long getFailedVaccinations() {
		return failedVaccinations;
	}

	public void setFailedVaccinations(long failedVaccinations) {
		this.failedVaccinations = failedVaccinations;
	}
}
