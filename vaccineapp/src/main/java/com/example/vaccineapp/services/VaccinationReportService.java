package com.example.vaccineapp.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vaccineapp.dto.VaccinationRateByRegionDTO;
import com.example.vaccineapp.dto.VaccinationReportDTO;
import com.example.vaccineapp.models.Vaccination;
import com.example.vaccineapp.models.Ward;
import com.example.vaccineapp.repositories.CitizenRepository;
import com.example.vaccineapp.repositories.VaccinationRepository;
import com.example.vaccineapp.repositories.WardRepository;

@Service
public class VaccinationReportService {
	@Autowired
	private VaccinationRepository vaccinationRepository;

	@Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private WardRepository wardRepository;
    
	public List<VaccinationReportDTO> getVaccinationReport(LocalDate startDate, LocalDate endDate) {
		return vaccinationRepository.findVaccinationReportByDateRange(startDate, endDate);
	}

	public List<VaccinationRateByRegionDTO> getVaccinationRateByRegion() {
        List<VaccinationRateByRegionDTO> report = new ArrayList<>();

        // Lấy tất cả các phường/xã
        List<Ward> wards = wardRepository.findAll();

        for (Ward ward : wards) {
            long totalCitizens = citizenRepository.countByWard(ward);  // Tổng số công dân trong khu vực

            // Lấy danh sách các mũi tiêm của khu vực
            List<Vaccination> vaccinations = vaccinationRepository.findByCitizen_Ward(ward);

            // Chuyển từ List<Vaccination> thành List<VaccinationRateByRegionDTO.VaccinationRecord>
            List<VaccinationRateByRegionDTO.VaccinationRecord> vaccinationRecords = vaccinations.stream()
                .map(vaccination -> new VaccinationRateByRegionDTO.VaccinationRecord(vaccination.getStatus().toString()))  // Chuyển mỗi Vaccination thành VaccinationRecord
                .collect(Collectors.toList());

            // Thêm dữ liệu vào danh sách kết quả
            report.add(new VaccinationRateByRegionDTO(ward.getName(), totalCitizens, vaccinationRecords));
        }

        return report;
    }
}
