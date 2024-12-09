package com.example.vaccineapp.repositories;

import com.example.vaccineapp.models.VaccinationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaccinationHistoryRepository extends JpaRepository<VaccinationHistory, Long> {
    List<VaccinationHistory> findByCitizenId(Long citizenId);
}