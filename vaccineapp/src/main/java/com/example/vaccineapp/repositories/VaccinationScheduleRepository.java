package com.example.vaccineapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vaccineapp.models.VaccinationSchedule;

@Repository
public interface VaccinationScheduleRepository extends JpaRepository<VaccinationSchedule, Long> {
}