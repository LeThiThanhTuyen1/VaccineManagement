package com.example.vaccineapp.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vaccineapp.models.Citizen;
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
    
    public Optional<Citizen> findCitizenByCccdD(String cccd) {
        return citizenRepository.findCitizenByCccd(cccd);
    }
    
    // Thêm mới công dân
    public Citizen addCitizen(Citizen citizen) {
        return citizenRepository.save(citizen);
    }
    
    // Cập nhật thông tin công dân
    public Citizen updateCitizen(Long citizenId, Citizen updatedCitizen) {
        // Kiểm tra công dân có tồn tại hay không
        Citizen existingCitizen = citizenRepository.findById(citizenId)
                .orElseThrow(() -> new RuntimeException("Công dân không tồn tại"));
        
        // Cập nhật thông tin
        existingCitizen.setFullName(updatedCitizen.getFullName());
        existingCitizen.setDateOfBirth(updatedCitizen.getDateOfBirth());
        existingCitizen.setPhoneNumber(updatedCitizen.getPhoneNumber());
        existingCitizen.setAddressDetail(updatedCitizen.getAddressDetail());
        existingCitizen.setTargetGroup(updatedCitizen.getTargetGroup());
        
        // Lưu thay đổi
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
