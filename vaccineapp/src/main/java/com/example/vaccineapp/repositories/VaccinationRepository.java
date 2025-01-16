package com.example.vaccineapp.repositories;

import com.example.vaccineapp.dto.VaccinationReportDTO;
import com.example.vaccineapp.models.Vaccination;
import com.example.vaccineapp.models.Vaccination.Status;
import com.example.vaccineapp.models.Ward;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

	List<Vaccination> findByCitizenId(Long citizenId);

	@Query("SELECT new com.example.vaccineapp.dto.VaccinationReportDTO(v.vaccinationDate, COUNT(v), "
		    + "SUM(CASE WHEN v.status = 'COMPLETED' THEN 1 ELSE 0 END), "
		    + "SUM(CASE WHEN v.status = 'PENDING' THEN 1 ELSE 0 END), "
		    + "SUM(CASE WHEN v.status = 'CANCELLED' THEN 1 ELSE 0 END)) "
		    + "FROM Vaccination v "
		    + "WHERE v.vaccinationDate BETWEEN :startDate AND :endDate "
		    + "GROUP BY v.vaccinationDate")
	List<VaccinationReportDTO> findVaccinationReportByDateRange(@Param("startDate") LocalDate startDate,
		                                                            @Param("endDate") LocalDate endDate);
	@Query("SELECT r FROM Vaccination r WHERE r.vaccine.name = :vaccineName")
	List<Vaccination> findByVaccineName(@Param("vaccineName") String vaccineName);

	@Query("SELECT r FROM Vaccination r WHERE " +
	       "(:vaccineName IS NULL OR r.vaccine.name = :vaccineName) AND " +
	       "(:vaccinationDate IS NULL OR r.vaccinationDate = :vaccinationDate) AND " +
	       "(:status IS NULL OR r.status = :status)")
	List<Vaccination> findByFilters(
	        @Param("vaccineName") String vaccineName,
	        @Param("vaccinationDate") LocalDate vaccinationDate,
	        @Param("status") Status status);

	List<Vaccination> findByCitizen_Ward(Ward ward);
	long countByCitizen_WardAndStatus(Ward ward, Vaccination.Status status);
}