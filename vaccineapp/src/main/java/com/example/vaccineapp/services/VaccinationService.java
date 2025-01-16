package com.example.vaccineapp.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vaccineapp.models.Citizen;
import com.example.vaccineapp.models.Vaccination;
import com.example.vaccineapp.models.Vaccine;
import com.example.vaccineapp.repositories.CitizenRepository;
import com.example.vaccineapp.repositories.VaccinationRepository;
import com.example.vaccineapp.repositories.VaccineRepository;

@Service
public class VaccinationService {
	@Autowired
	private VaccinationRepository vaccinationRepository;
	
	@Autowired
	private CitizenRepository citizenRepository;
	
	@Autowired
	private VaccineRepository vaccineRepository;
	
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
    
    public void registerVaccination(Long citizenId, Long vaccineId, LocalDate vaccinationDate) {
        // Lấy đối tượng Citizen và Vaccine từ database
        Citizen citizen = citizenRepository.findById(citizenId)
                                           .orElseThrow(() -> new IllegalArgumentException("Citizen not found"));
        Vaccine vaccine = vaccineRepository.findById(vaccineId)
                                           .orElseThrow(() -> new IllegalArgumentException("Vaccine not found"));

        // Tạo đối tượng Vaccination với thông tin từ form và status mặc định là PENDING
        Vaccination vaccination = new Vaccination();
        vaccination.setCitizen(citizen);
        vaccination.setVaccine(vaccine);
        vaccination.setVaccinationDate(vaccinationDate);
        vaccination.setStatus(Vaccination.Status.PENDING); // Trạng thái mặc định là PENDING

        // Lưu đối tượng Vaccination vào cơ sở dữ liệu
        vaccinationRepository.save(vaccination);
    }
    
}