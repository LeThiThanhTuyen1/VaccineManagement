package com.example.vaccineapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vaccineapp.models.Citizen;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {
}
