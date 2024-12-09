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
    	    WHERE LOWER(vh.citizen.fullName) LIKE LOWER(CONCAT('%', :name, '%'))
    	      AND vh.citizen.phoneNumber LIKE CONCAT('%', :phone, '%')
    	""")
    List<VaccinationHistory> findDetailedVaccinationHistory(
    	@Param("name") String name,
    	@Param("phone") String phone);
}