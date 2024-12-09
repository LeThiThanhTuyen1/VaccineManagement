package com.example.vaccineapp.repositories;

import com.example.vaccineapp.models.VaccinationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VaccinationHistoryRepository extends JpaRepository<VaccinationHistory, Long> {

    List<VaccinationHistory> findByCitizenId(Long citizenId);

    @Query("""
        SELECT vh
        FROM VaccinationHistory vh
        JOIN FETCH vh.citizen c
        JOIN FETCH c.ward w
        JOIN FETCH w.district d
        JOIN FETCH d.province p
        JOIN FETCH vh.vaccine v
        WHERE LOWER(c.fullName) LIKE LOWER(CONCAT('%', :name, '%'))
          AND c.phoneNumber LIKE CONCAT('%', :phone, '%')
    """)
    List<VaccinationHistory> findDetailedVaccinationHistory(
        @Param("name") String name,
        @Param("phone") String phone);
}