package com.example.vaccineapp.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vaccineapp.dto.VaccinationRateByRegionDTO;
import com.example.vaccineapp.dto.VaccinationReportDTO;
import com.example.vaccineapp.repositories.VaccinationRepository;

@Service
public class VaccinationReportService {
	@Autowired
	private VaccinationRepository vaccinationHistoryRepository;

	public List<VaccinationReportDTO> getVaccinationReport(LocalDate startDate, LocalDate endDate) {
		return vaccinationHistoryRepository.findVaccinationReportByDateRange(startDate, endDate);
	}

}
