package com.example.vaccineapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vaccineapp.models.Vaccine;
import com.example.vaccineapp.repositories.VaccineRepository;

@Service
public class VaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;

    public List<Vaccine> getAllVaccines() {
        return vaccineRepository.findAll();
    }

    
}
