package com.example.vaccineapp.repositories;

import com.example.vaccineapp.dto.VaccinationRateByRegionDTO;
import com.example.vaccineapp.dto.VaccinationReportDTO;
import com.example.vaccineapp.models.VaccinationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface VaccinationHistoryRepository extends JpaRepository<VaccinationHistory, Long> {

	List<VaccinationHistory> findByCitizenId(Long citizenId);

	@Query("""
			    SELECT vh
			    FROM VaccinationHistory vh
			    WHERE LOWER(vh.citizen.fullName) LIKE LOWER(CONCAT('%', :name, '%'))
			      AND vh.citizen.phoneNumber LIKE CONCAT('%', :phone, '%')
			""")
	List<VaccinationHistory> findDetailedVaccinationHistory(@Param("name") String name, @Param("phone") String phone);

	@Query("SELECT new com.example.vaccineapp.dto.VaccinationReportDTO(v.vaccinationDate, COUNT(v), "
			+ "SUM(CASE WHEN v.status = 'COMPLETED' THEN 1 ELSE 0 END), "
			+ "SUM(CASE WHEN v.status = 'MISSED' THEN 1 ELSE 0 END)) " + "FROM VaccinationHistory v "
			+ "WHERE v.vaccinationDate BETWEEN :startDate AND :endDate " + "GROUP BY v.vaccinationDate")
	List<VaccinationReportDTO> findVaccinationReportByDateRange(@Param("startDate") LocalDate startDate,
			@Param("endDate") LocalDate endDate);

	@Query("SELECT new com.example.vaccineapp.dto.VaccinationRateByRegionDTO(w.name, COUNT(c), "
			+ "SUM(CASE WHEN vh.id IS NOT NULL THEN 1 ELSE 0 END), "
			+ "(SUM(CASE WHEN vh.id IS NOT NULL THEN 1 ELSE 0 END) * 100.0) / COUNT(c)) "
			+ "FROM Citizen c LEFT JOIN VaccinationHistory vh ON c.id = vh.citizen.id "
			+ "JOIN Ward w ON c.ward.id = w.id " + "GROUP BY w.name")
	List<VaccinationRateByRegionDTO> findVaccinationRateByRegion();

}