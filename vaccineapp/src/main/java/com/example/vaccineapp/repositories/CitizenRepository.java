package com.example.vaccineapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vaccineapp.models.Citizen;
import com.example.vaccineapp.models.Citizen.TargetGroup;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {
	List<Citizen> findByTargetGroup(TargetGroup targetGroup);
	List<Citizen> findByFullNameContainingIgnoreCase(String fullName);
    List<Citizen> findByPhoneNumberContaining(String phoneNumber);
}
