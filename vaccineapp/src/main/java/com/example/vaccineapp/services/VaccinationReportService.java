package com.example.vaccineapp.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vaccineapp.dto.VaccinationRateByRegionDTO;
import com.example.vaccineapp.dto.VaccinationReportDTO;
import com.example.vaccineapp.models.Province;
import com.example.vaccineapp.repositories.VaccinationHistoryRepository;

@Service
public class VaccinationReportService {
	@Autowired
	private VaccinationHistoryRepository vaccinationHistoryRepository;

	public List<VaccinationReportDTO> getVaccinationReport(LocalDate startDate, LocalDate endDate) {
		return vaccinationHistoryRepository.findVaccinationReportByDateRange(startDate, endDate);
	}

	public List<VaccinationRateByRegionDTO> getVaccinationRateByRegion() {
        List<VaccinationRateByRegionDTO> report = new ArrayList<>();

        // Lấy danh sách các tỉnh
        List<Province> provinces = provinceRepository.findAll();
        
        // Duyệt qua từng tỉnh và tính toán tỷ lệ tiêm chủng
        for (Province province : provinces) {
            long totalCitizens = province.getDistricts().stream().mapToLong(d -> d.getWards().size()).sum(); // Tổng số dân
            long vaccinatedCitizens = province.getDistricts().stream().flatMap(d -> d.getWards().stream())
                    .filter(ward -> ward.isVaccinated()).count(); // Số người đã tiêm

            double vaccinationRate = (double) vaccinatedCitizens / totalCitizens * 100;

            // Tạo đối tượng DTO chứa tỷ lệ tiêm chủng và thêm vào báo cáo
            report.add(new VaccinationRateByRegionDTO(province.getName(), totalCitizens, vaccinatedCitizens, vaccinationRate));
        }

        return report;
	}
}
