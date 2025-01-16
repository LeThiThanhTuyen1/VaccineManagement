package com.example.vaccineapp.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vaccineapp.models.Citizen;
import com.example.vaccineapp.models.Vaccine;
import com.example.vaccineapp.models.Citizen.TargetGroup;
import com.example.vaccineapp.repositories.CitizenRepository;

@Service
public class CitizenService {
    @Autowired
    private CitizenRepository citizenRepository;
    
    public Citizen getCitizenById(Long citizenId) {
        // Tìm công dân theo ID hoặc ném ngoại lệ nếu không tìm thấy
        return citizenRepository.findById(citizenId)
                .orElseThrow(() -> new RuntimeException("Công dân không tồn tại"));
    }
    
    public List<Citizen> getAllCitizens() {
        return citizenRepository.findAll();
    }
    
    public List<Citizen> getCitizensByTargetGroup(TargetGroup targetGroup) {
        return citizenRepository.findByTargetGroup(targetGroup);
    }

    // nhớ xóa
    public List<Citizen> findAllCitizens() {
        return citizenRepository.findAll();
    }

    public List<Citizen> findCitizensByTargetGroup(TargetGroup targetGroup) {
        return citizenRepository.findByTargetGroup(targetGroup);
    }
    
    public boolean existsById(Long id) {
    	return citizenRepository.existsById(id);
    }
    
    public boolean existsByIdCccd(String cccd) {
        return citizenRepository.existsByCccd(cccd);
    }
    
    public Optional<Citizen> findById(Long id) {
        return citizenRepository.findById(id);
    }
    
    public boolean deleteById(Long id) {
        if (citizenRepository.existsById(id)) {
            citizenRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Thêm mới công dân
    public Citizen addCitizen(Citizen citizen) {
    	System.out.println();
    	if (citizenRepository.existsByCccd(citizen.getCccd())) {
            throw new RuntimeException("Số căn cước công dân đã tồn tại trong hệ thống!");
        }
    	
    	// Kiểm tra trùng số điện thoại
        if (citizenRepository.existsByPhoneNumber(citizen.getPhoneNumber())) {
            throw new RuntimeException("Số điện thoại đã tồn tại trong hệ thống!");
        }
        
        return citizenRepository.save(citizen);
    }
    
    public Citizen updateCitizen(Long citizenId, Citizen updatedCitizen) {
        // Lấy thông tin công dân hiện tại
        Citizen existingCitizen = citizenRepository.findById(citizenId)
                .orElseThrow(() -> new RuntimeException("Công dân không tồn tại"));
        
        // Kiểm tra trùng CCCD
        if (citizenRepository.existsByCccdAndIdNot(updatedCitizen.getCccd(), citizenId)) {
            throw new RuntimeException("Số căn cước công dân đã tồn tại trong hệ thống!");
        }

        // Kiểm tra trùng số điện thoại
        if (citizenRepository.existsByPhoneNumberAndIdNot(updatedCitizen.getPhoneNumber(), citizenId)) {
            throw new RuntimeException("Số điện thoại đã tồn tại trong hệ thống!");
        }

        // Cập nhật thông tin
        existingCitizen.setFullName(updatedCitizen.getFullName());
        existingCitizen.setDateOfBirth(updatedCitizen.getDateOfBirth());
        existingCitizen.setPhoneNumber(updatedCitizen.getPhoneNumber());
        existingCitizen.setWard(updatedCitizen.getWard());
        existingCitizen.setAddressDetail(updatedCitizen.getAddressDetail());
        existingCitizen.setTargetGroup(updatedCitizen.getTargetGroup());
        existingCitizen.setCccd(updatedCitizen.getCccd());

        // Lưu thông tin
        return citizenRepository.save(existingCitizen);
    }

    
    // Xóa công dân theo ID
    @Transactional
    public void deleteCitizenById(Long citizenId) {
        // Kiểm tra công dân có tồn tại hay không
        if (!citizenRepository.existsById(citizenId)) {
            throw new RuntimeException("Công dân không tồn tại");
        }
        citizenRepository.deleteById(citizenId);
    }
}
