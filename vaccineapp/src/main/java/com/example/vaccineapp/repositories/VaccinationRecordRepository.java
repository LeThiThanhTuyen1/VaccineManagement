package com.example.vaccineapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vaccineapp.models.VaccinationRecord;

@Repository
public interface VaccinationRecordRepository extends JpaRepository<VaccinationRecord, Long> {
}
