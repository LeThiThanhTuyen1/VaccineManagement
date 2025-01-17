package com.example.vaccineapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vaccineapp.models.Citizen;
import com.example.vaccineapp.models.Citizen.TargetGroup;
import com.example.vaccineapp.models.Ward;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {
	List<Citizen> findByTargetGroup(TargetGroup targetGroup);

	Citizen findCitizenByCccd(String cccd);

	boolean existsByCccd(String cccd);

	boolean existsByPhoneNumber(String phoneNumber);

	boolean existsByCccdAndIdNot(String cccd, Long id);

	boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);

	long countByWard(Ward ward);
}