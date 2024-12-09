package com.example.vaccineapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.vaccineapp.models.Citizen;
import com.example.vaccineapp.models.Citizen.TargetGroup;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {
	List<Citizen> findByTargetGroup(TargetGroup targetGroup);
	List<Citizen> findByFullNameContainingIgnoreCase(String fullName);
	List<Citizen> findByPhoneNumberContaining(String phoneNumber);
	 
	@Query("SELECT c FROM Citizen c WHERE " +
	           "LOWER(c.fullName) LIKE LOWER(CONCAT('%', :fullName, '%')) AND " +
	           "c.phoneNumber LIKE CONCAT('%', :phoneNumber, '%')")
	List<Citizen> findByNameAndPhone(@Param("fullName") String fullName, @Param("phoneNumber") String phoneNumber);
}
