package com.example.vaccineapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vaccineapp.models.Citizen;
import com.example.vaccineapp.models.Citizen.TargetGroup;
import com.example.vaccineapp.repositories.CitizenRepository;

@Service
public class CitizenService {
    @Autowired
    private CitizenRepository citizenRepository;

    public List<Citizen> getAllCitizens() {
        return citizenRepository.findAll();
    }
    
    public List<Citizen> getCitizensByTargetGroup(TargetGroup targetGroup) {
        return citizenRepository.findByTargetGroup(targetGroup);
    }

    public List<Citizen> findAllCitizens() {
        return citizenRepository.findAll();
    }

    public List<Citizen> findCitizensByTargetGroup(TargetGroup targetGroup) {
        return citizenRepository.findByTargetGroup(targetGroup);
    }
    
    public void applyVaccinationPolicy(Citizen citizen) {
        if (citizen.getTargetGroup() == TargetGroup.ELDERLY) {
            // Áp dụng chính sách cho người già
        } else if (citizen.getTargetGroup() == TargetGroup.PREGNANT_WOMEN) {
            // Áp dụng chính sách cho phụ nữ mang thai
        } 
        // Các chính sách khác
    }

}
