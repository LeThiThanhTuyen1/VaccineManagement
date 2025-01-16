package com.example.vaccineapp.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vaccineapp.models.Vaccination;
import com.example.vaccineapp.repositories.VaccinationRepository;

@Service
public class VaccinationService {
	@Autowired
	private VaccinationRepository vaccinationRepository;

	public List<Vaccination> getVaccinationHistoryByCitizenId(Long citizenId) {
		return vaccinationRepository.findByCitizenId(citizenId);
	}
	
	public List<Vaccination> getAllVaccinations() {
        return vaccinationRepository.findAll();
    }

    public List<Vaccination> filterByVaccineName(String vaccineName) {
        // Nếu không có tên vaccine, trả về toàn bộ danh sách
        if (vaccineName == null || vaccineName.isEmpty()) {
            return vaccinationRepository.findAll();
        }

        // Nếu có tên vaccine, trả về danh sách lọc
        return vaccinationRepository.findByVaccineName(vaccineName);
    }
    
    public Vaccination getVaccinationById(Long id) {
        return vaccinationRepository.findById(id).orElse(null);
    }

    public void saveVaccination(Vaccination vaccination) {
    	vaccinationRepository.save(vaccination);
    }

    public void deleteVaccinationById(Long id) {
    	vaccinationRepository.deleteById(id);
    }
    
    public List<Vaccination> getFilteredVaccinations(String vaccineName, LocalDate vaccinationDate, Vaccination.Status status) {
        return vaccinationRepository.findByFilters(vaccineName, vaccinationDate, status);
    }

    public void updateVaccinationStatus(Long vaccinationId, Vaccination.Status newStatus) {
        Vaccination vaccination = vaccinationRepository.findById(vaccinationId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy lịch tiêm với ID: " + vaccinationId));
        vaccination.setStatus(newStatus);
        vaccinationRepository.save(vaccination);
    }
}
