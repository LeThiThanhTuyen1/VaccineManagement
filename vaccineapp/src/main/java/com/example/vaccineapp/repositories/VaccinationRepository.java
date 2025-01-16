package com.example.vaccineapp.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.vaccineapp.models.Vaccinations;
import com.example.vaccineapp.models.VaccinationStatus;

@Repository
public interface VaccinationRepository extends JpaRepository<Vaccinations, Long> {

	// Tìm kiếm danh sách lịch tiêm theo tên vaccine
    @Query("SELECT r FROM Vaccinations r WHERE r.vaccine.name = :vaccineName")
    List<Vaccinations> findByVaccineName(@Param("vaccineName") String vaccineName);

    @Query("SELECT r FROM Vaccinations r WHERE " +
           "(:vaccineName IS NULL OR r.vaccine.name = :vaccineName) AND " +
           "(:vaccinationDate IS NULL OR r.vaccinationDate = :vaccinationDate) AND " +
           "(:status IS NULL OR r.status = :status)")
    List<Vaccinations> findByFilters(
            @Param("vaccineName") String vaccineName,
            @Param("vaccinationDate") LocalDate vaccinationDate,
            @Param("status") VaccinationStatus status);
}
