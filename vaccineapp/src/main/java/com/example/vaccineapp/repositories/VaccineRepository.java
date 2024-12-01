package com.example.vaccineapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vaccineapp.models.Vaccine;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
}
